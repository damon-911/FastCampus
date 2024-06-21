package fastcampus.part5.chapter4

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import fastcampus.part5.chapter4.features.common.viewmodel.ThemeViewModel

open class BaseDialogFragment : DialogFragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}