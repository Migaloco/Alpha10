package com.example.alpha10

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_percurso_categoria.*
import kotlinx.android.synthetic.main.activity_percurso_categoria.view.*
import kotlinx.android.synthetic.main.percurso_categoria_grid_indiv.view.*

class PercursoCategoria : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percurso_categoria)

        val bundle = intent.extras
        val list = bundle.getStringArrayList("list")
        val text = list[0]

        percurso_categoria_text.text = text
        list.remove(text)

        percurso_categoria_grid.adapter = CategorieAdapter(list, this)
    }

    class CategorieAdapter: BaseAdapter{

        var lt = ArrayList<String>()
        var context: Context? = null

        constructor(lt : ArrayList<String>, context: Context): super(){

            this.lt = lt
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val percurso = this.lt[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var categorieView = inflator.inflate(R.layout.percurso_categoria_grid_indiv, null)

            categorieView.percurso_categoria_grid_indiv_text.text = percurso

            categorieView.setOnClickListener {

                val intent = Intent(context, PercursosIndivid::class.java)
                intent.putExtra("name", percurso)
                context!!.startActivity(intent)
            }
            return categorieView
        }

        override fun getItem(p0: Int): Any {

            return lt[p0]
        }

        override fun getItemId(p0: Int): Long {

            return p0.toLong()
        }

        override fun getCount(): Int {

            return lt.size
        }
    }
}
