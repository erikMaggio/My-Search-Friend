package com.example.mysearchfriend.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.mysearchfriend.AppMySearchFriend.Companion.preferences
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.ActivityLoginBinding
import com.example.mysearchfriend.model.fireStore.UserFireStore
import com.example.mysearchfriend.ui.fragment.SignUpFragment
import com.example.mysearchfriend.utils.Globals.EMAIL
import com.example.mysearchfriend.utils.Globals.NAME
import com.example.mysearchfriend.utils.Globals.OBJECT_USER
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception

class LoginActivity : AppCompatActivity() {


    val prueba = UserFireStore()

    private val GOOGLE_SIGN = 100
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLoginGoogle.setOnClickListener {
            loginGoogle()
        }
        navigationLoginFireStore()
        navigationRegisterFireStore()
    }

    private fun loginGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("108240263524-jlhr15fdi0l6a0opv06fo8bm54ikh9gk.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()
        startActivityForResult(googleSignInClient.signInIntent, GOOGLE_SIGN)
    }

    //conection whit Google Acount
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful)
                                Log.d("success", account.email.toString())

                            preferences.saveUserEmail(account.email.toString())
                            EMAIL = account.email.toString()
                            NAME = account.givenName.toString()

                            loginFireStore(preferences.getUserEmail())

                            startActivity(Intent(this, OnBoardingActivity::class.java))
                        }

                } else {
                    Toast.makeText(this, "Error Else", Toast.LENGTH_LONG).show()

                }

            } catch (e: Exception) {
                Toast.makeText(this, "Error En Catch", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loginFireStore(email: String) {
        prueba.getUser(email).observe(this) {
            if (it.fullName != "empty") {
                OBJECT_USER = it
                startActivity(Intent(this, DrawerActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }
        }
    }

    private fun navigationRegisterFireStore(){
        binding.btRegister.setOnClickListener {
            startActivity(Intent(this,SignUpFragment::class.java))
        }
    }

    private fun navigationLoginFireStore(){
        binding.btLoginFireStore.setOnClickListener{
            startActivity(Intent(this,ActivityLoginFireStore::class.java))
        }
    }
        //acceso con invitado

//        binding.btInvited.setOnClickListener {
//            startActivity(Intent(this,OnBoardingActivity::class.java))
//        }

}