package fastcampus.part5.chapter4.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import fastcampus.part5.chapter4.core.viewmodel.ThemeViewModel

open class BaseFragment : Fragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}