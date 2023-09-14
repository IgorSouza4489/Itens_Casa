package com.example.nocompose

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nocompose.databinding.FragmentHomeBinding
import es.dmoral.toasty.Toasty
import rvAdapter



class HomeFragment : Fragment(), rvAdapter.ListaClickInterface {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var reuniaoArrayList : ArrayList<modelitem>
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: rvAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = binding.root


        val fragmentManager = childFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)


        recyclerView = view.findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)


        reuniaoArrayList = arrayListOf()
        adapter = rvAdapter(reuniaoArrayList, this)

        recyclerView.adapter = adapter

        viewModel.itens.observe(viewLifecycleOwner) { list ->
            list?.let {
                reuniaoArrayList.clear()
                reuniaoArrayList.addAll(it)
                adapter.updateList(it)
                binding.textView4.text = adapter.itemCount.toString()

            }
        }

        // Configurar o comportamento do botão de voltar personalizado

        createItem()
        return view
    }

    fun createItem(){
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment2_to_createFragment4)

        }
    }

    override fun onListaClick(modelitem: modelitem) {
        //Toast.makeText(requireContext(), "item: ${modelitem.documentId}", Toast.LENGTH_LONG).show()
        modelitem.documentId?.let { modelitem.check?.let { it1 ->
            viewModel.atualizarValorNoFirebase(it,
                it1
            )
        } }

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}