package com.buyin.dalili.features.bot.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.buyin.dalili.databinding.FragmentBotBinding
import com.buyin.dalili.features.bot.domain.model.MassegeModel
import com.buyin.dalili.features.bot.domain.usecase.Answers


class botFragment : Fragment() {
    private lateinit var binding: FragmentBotBinding
    private  lateinit var adapter: MassegeAdapter

    private lateinit var MassegeList:ArrayList<MassegeModel>
    private val MESSAGE="مرحبا بك انا دليلي كيف يمكنني مساعدتك؟"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     init()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding= FragmentBotBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun clickEvent() {
        binding.btnSend.setOnClickListener(View.OnClickListener {
            sendMessage()
        })
    }

    private fun sendMessage() {
        val message = binding.etMasseage.text.toString()
        if (message.isNotEmpty()) {
            binding.etMasseage.setText("")
            adapter.setNewMassege(MassegeModel(text =  message, isBot =  false))
//            binding.rvChat.scrollToPosition(adapter.itemCount - 1)
            botResponse(message)
        }
    }

    private fun initRV() {
        MassegeList =  ArrayList()

        adapter  = MassegeAdapter(MassegeList)
        binding.rvChat.adapter = adapter
        binding.rvChat.suppressLayout(false)

    }
//    private fun customMessage(message: String) {
//        adapter.setNewMassege(MassegeModel(message))
//        binding.rvChat.scrollToPosition(adapter.itemCount - 1)
//    }

    private fun botResponse(message: String) {
        val respone = Answers.Questions(message)
        adapter.setNewMassege(MassegeModel(respone))

    }
    private fun init()
    {
        initRV()
        FirstMessage(MassegeModel(MESSAGE,true))
        clickEvent()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun FirstMessage(model: MassegeModel)
    {
        adapter.setNewMassege(model)
        adapter.notifyDataSetChanged()
    }


}
