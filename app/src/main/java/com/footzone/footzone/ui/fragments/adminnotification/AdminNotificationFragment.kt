package com.footzone.footzone.ui.fragments.adminnotification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.footzone.footzone.R
import com.footzone.footzone.adapter.AdminNotificationAdapter
import com.footzone.footzone.adapter.UserNotificationAdapter
import com.footzone.footzone.databinding.FragmentAdminNotificationBinding
import com.footzone.footzone.model.AdminNotification
import com.footzone.footzone.ui.fragments.BaseFragment
import java.util.ArrayList

class AdminNotificationFragment : BaseFragment(R.layout.fragment_admin_notification) {

    lateinit var binding: FragmentAdminNotificationBinding
    lateinit var adapter: AdminNotificationAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminNotificationBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        refreshAdapter()
    }

    private fun refreshAdapter() {
        adapter = AdminNotificationAdapter(getAllAdminNotifications())
        binding.rvAdminNotification.adapter = adapter
    }

    private fun getAllAdminNotifications(): ArrayList<AdminNotification> {
        val items = ArrayList<AdminNotification>()
        items.add(AdminNotification("Hello"))
        items.add(AdminNotification("Hello"))
        items.add(AdminNotification("Hello"))
        items.add(AdminNotification("Hello"))
        items.add(AdminNotification("Hello"))
        items.add(AdminNotification("Hello"))
        items.add(AdminNotification("Hello"))
        return items
    }
}