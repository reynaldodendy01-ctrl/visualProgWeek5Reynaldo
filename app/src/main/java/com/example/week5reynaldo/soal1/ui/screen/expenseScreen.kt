package com.example.week5reynaldo.soal1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5reynaldo.soal1.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = viewModel()) {
    val expenseList by viewModel.expenseList.collectAsState()
    val nama by viewModel.nama.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val namaError by viewModel.namaError.collectAsState()
    val amountError by viewModel.amountError.collectAsState()

    val lagiEdit by viewModel.lagiEdit.collectAsState()
    val editanText by viewModel.editAmount.collectAsState()
    val editanError by viewModel.editError.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp, start = 14.dp, end = 14.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Expenses", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Total Expenses : ${viewModel.nominalFormat(viewModel.total())}", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = nama,
                onValueChange = viewModel::namaChange,
                label = { Text("Expense Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = namaError != null,
                supportingText = {
                    if (namaError != null) {
                        Text(namaError!!, fontSize = 10.sp)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorBorderColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorCursorColor = Color.Red,
                    errorSupportingTextColor = Color.Red
                )
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = amount,
                onValueChange = viewModel::amountChange,
                label = { Text("Amount of Expense") },
                modifier = Modifier.weight(1f),
                isError = amountError != null,
                supportingText = {
                    if (amountError != null) {
                        Text(amountError!!, fontSize = 10.sp)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorBorderColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorCursorColor = Color.Red,
                    errorSupportingTextColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = viewModel::addExpense,
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Gray
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Expense", tint = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        val edit = lagiEdit
        if (edit != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Edit ${edit.name}", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = editanText,
                        onValueChange = viewModel::editAmount,
                        label = { Text("Amount of Expense") },
                        singleLine = true,
                        isError = editanError,
                        supportingText = {
                            if (editanError) {
                                Text("Amount must be greater than 0", fontSize = 10.sp)
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue,
                            unfocusedLabelColor = Color.Gray,
                            cursorColor = Color.Blue,
                            errorBorderColor = Color.Red,
                            errorLabelColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorSupportingTextColor = Color.Red
                        )
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        TextButton(onClick = viewModel::cancel) {
                            Text("Cancel", color = Color.Red)
                        }
                        TextButton(onClick = viewModel::save) {
                            Text("Save", color = Color.Black)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(expenseList, key = { it.id }) { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDCE2F9)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 14.dp, end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(3f)
                                .padding(vertical = 20.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Name : ${item.name}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${viewModel.nominalFormat(item.amount)}", fontSize = 14.sp)
                        }
                        IconButton(
                            onClick = { viewModel.edit(item) }
                        ) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color(0xFFC99700))
                        }
                        IconButton(
                            onClick = { viewModel.deleteExpense(item.id) }
                        ) {
                            Icon(Icons.Filled.Delete, contentDescription = "Remove", tint = Color.Red)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpenseScreenPreview() {
    ExpenseScreen()
}
