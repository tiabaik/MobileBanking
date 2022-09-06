package com.tia_0653.mobilebanking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


class FragmentLogin : Fragment() {
    private lateinit var btnLogin: Button
    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilPassword: TextInputLayout

    // array of accepted username and password
    private val accepted = arrayOf(
        "admin" to "0653",
        "user" to "user"
    )

    // check if username and password is valid (copilot)
    private fun isValid(username: String, password: String): Boolean {
        return accepted.contains(username to password)
    }//dss



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    // pakai onViewCreated untuk mem-bind view yang sudah di inflate
    // --> https://stackoverflow.com/questions/51672231/kotlin-button-onclicklistener-event-inside-a-fragment && copilot
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin = view.findViewById(R.id.btnLogin)
        tilUsername = view.findViewById(R.id.tilLoginEmail)
        tilPassword = view.findViewById(R.id.tilLoginPassword)

        btnLogin.setOnClickListener {
            val username = tilUsername.editText?.text.toString()
            val password = tilPassword.editText?.text.toString()

            if (isValid(username, password)) {
                // login success
                Snackbar.make(view, "Login success", Snackbar.LENGTH_SHORT).show()

                // go to home activity
                (activity as MainActivity).goToHome()

            } else {
                // login failed
                Snackbar.make(view, "Login failed", Snackbar.LENGTH_SHORT).show()
            }
        }

        // txtRegisChangeFragment on click
        view.findViewById<TextView>(R.id.txtLoginChangeFragment).setOnClickListener {
            val fragment = FragmentRegister()
            (activity as MainActivity).changeFragment(fragment)
        }
    }
}