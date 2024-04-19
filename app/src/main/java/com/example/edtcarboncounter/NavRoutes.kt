package com.example.edtcarboncounter

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("Home")
    object InstructionManual: NavRoutes("InstructionManual")
    object ReceiptRecall: NavRoutes("ReceiptRecall")
    object CounterMaterial: NavRoutes("CounterMaterial")
    object CounterReceipt: NavRoutes("CounterReceipt")
    object CounterRecycled: NavRoutes("CounterRecycled")
    object DatabaseAdd: NavRoutes("DatabaseAdd")
    object DatabaseHome: NavRoutes("DatabaseHome")
    object DatabaseSearch: NavRoutes("DatabaseSearch")
    object DatabaseUpdate: NavRoutes("DatabaseUpdate")
    object CarbonComparison: NavRoutes("CarbonComparison")
    object CounterProject: NavRoutes("CounterProject")
    object AddMaterial: NavRoutes("AddMaterial")
    object AddTransport: NavRoutes("AddTransport")
}
