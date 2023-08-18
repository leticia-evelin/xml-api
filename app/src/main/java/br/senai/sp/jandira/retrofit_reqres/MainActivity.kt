package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //Ação do botão GET
        findViewById<Button>(R.id.btnGet).setOnClickListener{
            getUserById()
        }

        //Ação do botão POST
        findViewById<Button>(R.id.btnPost).setOnClickListener{
            createUser()
        }

        //Ação do botão PUT
        findViewById<Button>(R.id.btnPut).setOnClickListener{
            updateUser()
        }

        //Ação do botão DELETE
        findViewById<Button>(R.id.btnDelete).setOnClickListener{
            deleteUser()
        }
    }

    private fun getUserById() {
        //requisições assíncronas
        lifecycleScope.launch {
            //Chamada para endpoint
            var result = apiService.getUserById("8")

            //teste para console
            if (result.isSuccessful){
                Log.e("GETTING-DATA", "${result.body()?.data}")

            } else {
                Log.e("GETTING-DATA", "${result.message()}")
            }
        }
    }

    //Insere dados de usuario
    private fun createUser(){
        lifecycleScope.launch {

            val body = JsonObject().apply {
                addProperty("name", "Leticia")
                addProperty("job", "Desenvolvedora")
            }

            val result = apiService.createUser(body)

            //teste para console
            if (result.isSuccessful){
                Log.e("CREATE-DATA", "${result.body()}")

            } else {
                Log.e("CREATE-DATA", "${result.message()}")
            }

        }

    }

    // Atualiza dados de usuario
    private fun updateUser(){
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Leticia")
                addProperty("job", "Desenvolvedora")
            }

            val result = apiService.updateUser("2", body)

            //teste para console
            if (result.isSuccessful){
                Log.e("UPDATE-DATA", "${result.body()}")

            } else {
                Log.e("UPDATE-DATA", "${result.message()}")
            }

        }
    }

    //Apaga usuario
    private fun deleteUser(){
        lifecycleScope.launch {
            val result = apiService.deleteUser("2")

            //teste para console
            if (result.isSuccessful){
                Log.e("DELETE-DATA", "${result}")

            } else {
                Log.e("DELETE-DATA", "${result.message()}")
            }

        }
    }
}