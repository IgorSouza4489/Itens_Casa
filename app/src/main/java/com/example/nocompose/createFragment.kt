import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.nocompose.CreateViewModel
import com.example.nocompose.R
import com.example.nocompose.databinding.FragmentCreateBinding
import com.google.android.material.snackbar.Snackbar
import es.dmoral.toasty.Toasty

class createFragment : Fragment() {


    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
        val view = binding.root

        viewModel.msg.observe(viewLifecycleOwner){
            if ( it.isNotBlank()) {
                showSnackbar(view, it)
            }
        }

        viewModel.status.observe(viewLifecycleOwner){
            if (it){

                findNavController().popBackStack()
            }
//            if (it){
//                binding.inputMarca.setText("")
//                binding.inputModelo.setText("")
//                binding.inputPlaca.setText("")
//            }
        }
        inserir()
        return view
    }

    fun inserir() {
        binding.button.setOnClickListener {
            val objeto = binding.tv111.text.toString()
            val precoString = binding.tv222.text.toString()

            if (objeto.isNotEmpty() && precoString.isNotEmpty()) {
                try {
                    val preco = precoString.toInt()
                    viewModel.inserir(objeto, preco, check = false)
                } catch (e: NumberFormatException) {
                    Toast.makeText(requireContext(), "Preço deve ser um número válido", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showSnackbar(view: View, msg: String) {
        Toasty.success(requireContext(), msg, Toast.LENGTH_SHORT, true).show();

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        // Configurar o comportamento personalizado do botão de voltar
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Verificar se há um Fragment anterior na pilha de navegação
            if (findNavController().previousBackStackEntry != null) {
                // Realizar a navegação para o Fragment anterior (homeFragment2) com animação personalizada
                findNavController().navigate(
                    R.id.homeFragment2,
                    null,
                    NavOptions.Builder()
                        .setEnterAnim(androidx.appcompat.R.anim.abc_fade_in)   // Sua animação de entrada personalizada
                        .setExitAnim(androidx.appcompat.R.anim.abc_fade_out)  // Sua animação de saída personalizada
                        .build()
                )
            } else {
                // Se não houver Fragment anterior, chame o comportamento padrão do botão de voltar
                activity?.onBackPressed()
            }
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}
