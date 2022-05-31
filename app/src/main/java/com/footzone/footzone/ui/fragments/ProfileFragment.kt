package com.footzone.footzone.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.footzone.footzone.R
import com.footzone.footzone.databinding.FragmentProfileBinding
import com.footzone.footzone.utils.SharedPref
import java.io.File

class ProfileFragment : Fragment() {
    lateinit var sharedPref: SharedPref
    private val PICK_FROM_FILE_ADD: Int = 1001
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        sharedPref= SharedPref(requireContext())

        val LogIn= sharedPref.getLogIn("LogIn",false)
        if (!LogIn){
            binding.linerProfile.visibility=View.GONE
            binding.linearProfileNoSignIn.visibility=View.VISIBLE
        }else{
            binding.linerProfile.visibility=View.VISIBLE
            binding.linearProfileNoSignIn.visibility=View.GONE

        }
        binding.ivAdd.setOnClickListener {
            openLocalStorage()
        }
        binding.tvEnterAccount.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
        }
    }

    private fun openLocalStorage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) intent.action = Intent.ACTION_GET_CONTENT
        val chooserIntent = Intent.createChooser(intent, "Complete action using")

        val capturedImage = System.currentTimeMillis().toString() + ".jpg"
        val file = File(Environment.getExternalStorageDirectory(), capturedImage)
        //val outputFileUri = Uri.fromFile(file)

        startActivityForResult(chooserIntent, PICK_FROM_FILE_ADD)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FROM_FILE_ADD && resultCode == RESULT_OK) {
            try {
                val selectedImageUri: Uri = data?.data!!
                if (selectedImageUri.toString().isNotBlank()) {
                    binding.ivDefaultPerson.visibility = View.GONE
                    binding.ivProfile.setImageURI(selectedImageUri)
                    binding.ivAdd.setImageResource(R.drawable.ic_edit_button)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}