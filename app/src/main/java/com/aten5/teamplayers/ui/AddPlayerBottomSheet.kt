package com.aten5.teamplayers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aten5.teamplayers.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_add_player.*

class AddPlayerBottomSheet : BottomSheetDialogFragment() {
    private lateinit var selectionLister: (Boolean) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_save.setOnClickListener {
            if (::selectionLister.isInitialized) selectionLister(true).also { dismiss() }
        }

        btn_ignore.setOnClickListener {
            if (::selectionLister.isInitialized) selectionLister(false).also { dismiss() }
        }
    }

    companion object {
        fun newInstance(selectionListener: (Boolean) -> Unit): AddPlayerBottomSheet {
            val fragment = AddPlayerBottomSheet()
            fragment.selectionLister = selectionListener
            return fragment
        }
    }
}