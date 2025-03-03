package br.ufg.inf.drtransferapp.listPatients.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.listPatients.model.PatientResponseModel
import br.ufg.inf.drtransferapp.utils.extension.calculateAgeFromISODate

class ListPatientsAdapter(
    private val onClickEdit: (PatientResponseModel) -> Unit,
    private val onClickDelete: (PatientResponseModel) -> Unit
) : RecyclerView.Adapter<ListPatientsAdapter.ViewHolder>() {

    private var listPatients: List<PatientResponseModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPatientsAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = listPatients[position]
        holder.nome.text = patient.person.name
        holder.cpf.text = patient.person.cpf
        holder.telefone.text = patient.person.phone
        holder.tipoSanguineo.text = patient.bloodType
        holder.genero.text = patient.person.gender
        holder.idade.text = patient.birthDate.calculateAgeFromISODate()
        holder.edit.setOnClickListener {
            onClickEdit(patient)
        }
        holder.delete.setOnClickListener {
            onClickDelete(patient)
        }
    }

    override fun getItemCount() = listPatients.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.tv_name_conteudo)
        val cpf: TextView = itemView.findViewById(R.id.tv_cpf_conteudo)
        val telefone: TextView = itemView.findViewById(R.id.tv_telefone_conteudo)
        val tipoSanguineo: TextView = itemView.findViewById(R.id.tv_tipo_sangue_conteudo)
        val genero: TextView = itemView.findViewById(R.id.tv_genero_conteudo)
        val idade: TextView = itemView.findViewById(R.id.tv_idade_conteudo)
        val edit: ImageView = itemView.findViewById(R.id.iv_edit)
        val delete: ImageView = itemView.findViewById(R.id.iv_delete)
    }

    fun updateList(list: List<PatientResponseModel>) {
        listPatients = list
        notifyDataSetChanged()
    }
}