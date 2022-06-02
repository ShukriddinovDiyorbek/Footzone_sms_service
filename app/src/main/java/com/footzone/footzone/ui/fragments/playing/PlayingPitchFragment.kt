package com.footzone.footzone.ui.fragments.playing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.footzone.footzone.R
import com.footzone.footzone.adapter.PlayingPitchAdapter
import com.footzone.footzone.databinding.FragmentPlayingPitchBinding
import com.footzone.footzone.model.Hour
import com.footzone.footzone.model.PitchHistory
import com.footzone.footzone.ui.fragments.BaseFragment
import java.util.ArrayList

class PlayingPitchFragment : BaseFragment(R.layout.fragment_playing_pitch) {

    private lateinit var binding: FragmentPlayingPitchBinding
    private lateinit var playingPitchAdapter: PlayingPitchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPlayingPitchBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        playingPitchAdapter = PlayingPitchAdapter()
        playingPitchAdapter.submitData(getPlayedPitchHistory())
        refreshAdapter()
    }

    private fun refreshAdapter() {
        binding.rvPlayingPitches.adapter = playingPitchAdapter
    }

    private fun getPlayedPitchHistory(): ArrayList<PitchHistory> {
        return ArrayList<PitchHistory>().apply {
            for (i in 0..5) {
                this.add(PitchHistory("Acme", "29-may, chorshanba", Hour("16:00", "18:00"), 100000))
            }
        }
    }
}