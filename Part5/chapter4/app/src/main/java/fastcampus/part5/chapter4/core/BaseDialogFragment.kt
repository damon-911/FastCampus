package fastcampus.part5.chapter4.core

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import fastcampus.part5.chapter4.core.viewmodel.ThemeViewModel

open class BaseDialogFragment : DialogFragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}