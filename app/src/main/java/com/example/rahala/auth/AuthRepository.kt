package com.example.rahala.auth


import android.app.Activity
import android.content.Context
import com.google.firebase.auth.*
import com.google.android.gms.auth.api.*
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import com.google.firebase.FirebaseException


class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    /** 🔹 Authentification avec Email & Mot de Passe */
    suspend fun signInWithEmail(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /** 🔹 Authentification avec Google */
    fun signInWithGoogle(idToken: String, onComplete: (Boolean, String?) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) onComplete(true, null)
                else onComplete(false, task.exception?.message)
            }
    }

    /** 🔹 Authentification avec Numéro de Téléphone */
    fun sendVerificationCode(
        phoneNumber: String,
        activity: Activity,
        onCodeSent: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    auth.signInWithCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    onError(e.message ?: "Erreur d’authentification")
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    onCodeSent(verificationId)
                }
            }).build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtpCode(verificationId: String, code: String, onComplete: (Boolean, String?) -> Unit) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) onComplete(true, null)
                else onComplete(false, task.exception?.message)
            }
    }
}
