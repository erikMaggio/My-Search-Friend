package com.example.mysearchfriend.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mysearchfriend.AppMySearchFriend.Companion.preferences
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.FragmentLoginBinding
import com.example.mysearchfriend.model.fireStore.UserFireStore
import com.example.mysearchfriend.model.response.State
import com.example.mysearchfriend.ui.home.DrawerActivity
import com.example.mysearchfriend.ui.onboarding.OnBoardingActivity
import com.example.mysearchfriend.utils.Globals.EMAIL
import com.example.mysearchfriend.utils.Globals.NAME
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception

class LoginFragment : Fragment() {


    val prueba = UserFireStore()
    private val GOOGLE_SIGN = 100
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)



        actions()

        return binding.root
    }


    private fun actions() {
        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        binding.btLoginFireStore.setOnClickListener {
            findNavController().navigate(R.id.loginFireStoreFragment)
        }

        binding.btLoginGoogle.setOnClickListener {
            loginGoogle()
        }
    }

    private fun loginGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("108240263524-jlhr15fdi0l6a0opv06fo8bm54ikh9gk.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
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

                            //loginFireStore(preferences.getUserEmail())
                            startActivity(Intent(context, OnBoardingActivity::class.java))
                        }

                } else {
                    Toast.makeText(context, "Error Else", Toast.LENGTH_LONG).show()

                }

            } catch (e: Exception) {
                Toast.makeText(context, "Error En Catch", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun controlState(state:State){
        when (state){
           State.SUCCESS ->{}
            State.LOADING ->{}
            State.ERROR ->{}
            State.EMPTY ->{}
            State.ERROR_PASSWORD ->{}
        }
    }
}





