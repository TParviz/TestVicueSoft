package tj.test.testvicuesoft.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import tj.test.testvicuesoft.databinding.DialogAddTextBinding

@AndroidEntryPoint
class AddTextDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: DialogAddTextBinding
    private lateinit var eventListener: (String) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout = bottomSheetDialog
                .findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { parent ->
                BottomSheetBehavior.from(parent).apply {
                    state = BottomSheetBehavior.STATE_EXPANDED
                    skipCollapsed = true
                }
            }
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        btnApply.setOnClickListener {
            eventListener.invoke(binding.etTitleInput.text.toString())
            dismiss()
        }
    }

    fun setListeners(callback: (String) -> Unit) {
        this.eventListener = callback
    }

    companion object {
        fun newInstance(): AddTextDialogFragment {
            return AddTextDialogFragment()
        }

        const val TAG_ADDTEXT_DIALOG_FRAGMENT = "AddTextDialogFragment"
    }
}