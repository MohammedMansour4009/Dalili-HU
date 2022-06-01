package com.buyin.dalili.features.material.sources.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.buyin.dalili.databinding.FragmentViewSourcesBinding
import com.buyin.dalili.features.material.sources.presentation.ui.SourcesFragment.Companion.SOURCE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewSourcesFragment : Fragment() {

    private lateinit var binding: FragmentViewSourcesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val url = arguments?.getString(SOURCE_URL).toString()

        binding.wepView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }

        binding.wepView.loadUrl(url)

        val webSettings = binding.wepView.settings
        webSettings.javaScriptEnabled = true
        binding.wepView.settings.javaScriptCanOpenWindowsAutomatically = true
        binding.wepView.clearCache(true)
        binding.wepView.clearHistory()
    }


}