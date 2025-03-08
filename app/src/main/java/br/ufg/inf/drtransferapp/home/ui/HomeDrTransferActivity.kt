package br.ufg.inf.drtransferapp.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.ufg.inf.drtransferapp.R
import br.ufg.inf.drtransferapp.home.ui.color.Blue
import br.ufg.inf.drtransferapp.home.ui.theme.AppTheme
import br.ufg.inf.drtransferapp.home.ui.typography.Typography
import br.ufg.inf.drtransferapp.patient.listPatients.view.ListPatientActivity

class HomeDrTransferActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreen(this)
            }
        }
    }
}

@Composable
fun HomeScreen(context: Context) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top, // Organiza os itens no topo da tela
            horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente
        ) {
            Text(
                text = "Dr Transfer",
                style = Typography.bodyLarge.copy(
                    fontFamily = FontFamily(Font(R.font.yrsa_semibold)),
                    fontSize = 32.sp,
                    color = Blue
                ),
                modifier = Modifier.padding(top = 54.dp)
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre o título e a imagem

            Image(
                painter = painterResource(id = R.drawable.img_doctor), // Substitua com o ID da sua imagem
                contentDescription = "Imagem Doutor",
                modifier = Modifier.size(150.dp) // Define o tamanho da imagem
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Grid com 2 colunas e 4 botões
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Define 2 colunas
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(botaoItems) { item ->
                    ButtonWithIcon(
                        context,
                        iconRes = item.iconRes,
                        label = item.label
                    )
                }
            }
        }
    }
}

private val botaoItems = listOf(
    BotaoItem(R.drawable.patient, "Paciente"),
    BotaoItem(R.drawable.transport, "Transporte"),
    BotaoItem(R.drawable.doctor, "Médico"),
    BotaoItem(R.drawable.drugs, "Drogas")
)

data class BotaoItem(val iconRes: Int, val label: String)

@Composable
fun ButtonWithIcon(context: Context, iconRes: Int, label: String) {
    Button(
        onClick = {
            when (label) {
                "Paciente" -> {
                    val intent = Intent(context, ListPatientActivity::class.java)
                    context.startActivity(intent)
                }
                "Transporte" -> {
                    // Lógica para o botão "Transporte"
                    // Navegar para a tela de transporte
                }
                "Médico" -> {
                    // Lógica para o botão "Médico"
                }
                "Drogas" -> {
                    // Lógica para o botão "Drogas"
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(120.dp) // Define tamanho quadrado
            .padding(8.dp) // Espaçamento entre botões
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(label)
        }
    }
}

//@Preview
//@Composable
//fun HomeScreenPreview() {
//    AppTheme {
//        HomeScreen(this)
//    }
//}
//
//@Preview
//@Composable
//fun ButtonWithIconPreview() {
//    ButtonWithIcon(this, iconRes = R.drawable.img_doctor, label = "Paciente")
//}
