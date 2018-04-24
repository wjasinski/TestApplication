package  com.wjasinski.myapplication.data

import android.content.Context
import android.util.Log
import com.wjasinski.myapplication.data.database.RecipesDao
import com.wjasinski.myapplication.data.net.RestService
import com.wjasinski.myapplication.model.Recipe
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager



class RecipesRepository @Inject constructor(val restService: RestService, val recipesDao: RecipesDao, val context: Context) {
    fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun getRecipes(): Flowable<List<Recipe>> {
        return if(isOnline()) Flowable.concatArray(
                getRecipesFromDb(),
                getRecipesFromApi()
        ) else getRecipesFromDb()
    }

    fun getRecipesFromDb(): Flowable<List<Recipe>> {
        return recipesDao.getAll().filter {it.isNotEmpty()}
                .toFlowable().doOnNext {
                    Log.d("RecipesRepository", "Dispatching ${it.size} users from DB...")
                }
    }

    fun getRecipesFromApi(): Flowable<List<Recipe>> {
        return restService.getRecipes().doOnNext {
            Log.d("RecipesRepository", "Dispatching ${it.size} users from API...")
            storeRecipesInDb(it)
        }
    }

    private fun storeRecipesInDb(recipes: List<Recipe>) {
        Flowable.fromCallable { recipesDao.insertAll(recipes) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Log.d("RecipesRepository", "Inserted ${recipes.size} users from API in DB...")
                }
    }
}