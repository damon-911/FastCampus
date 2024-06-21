package fastcampus.part5.chapter4

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import fastcampus.part5.chapter4.features.common.viewmodel.ThemeViewModel

open class BaseFragment : Fragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}