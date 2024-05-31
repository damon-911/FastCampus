package fastcampus.part4.chapter5_plus.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fastcampus.part4.chapter5_plus.ui.theme.Chapter5_plusTheme

@Composable
fun NavigationExample(
    modifier: Modifier = Modifier,
    // NavController : 컴포저블의 백스택을 추적함
    // rememberNavController()를 사용하여 NavController 생성
    navController: NavHostController = rememberNavController()
) {
    // NavHost 생성
    // NavController와 Navigation Graph를 연결
    // 해당 그래프의 시작 대상 경로부터 출발하여 설정해놓은 각각의 경로와 연결됨
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = modifier.padding(16.dp)
    ) {
        composable("Home") {
            Column {
                Text("Home")
                Button(onClick = {
                    navController.navigate("Office") {
                        // popUpTo() : 스택에서 해당 요소를 찾아서 그 사이에 있는 것을 없애버림
                        // 이동 순서를 Home -> Office -> Playground 라 하자
                        // (현재 스택) Home -> Office
                        // 여기서 Playground로 이동하면 Home과 Playground 사이에 있는 Office가 스택에서 사라짐
                        // (현재 스택) Home -> Playground
                        // 여기서 백 버튼을 누르면 Home으로 이동됨
                        popUpTo("Home") {
                            // inclusive = true : 스택에서 해당 요소를 찾아서 해당 요소를 포함한 그 사이에 있는 것을 모두 없애버림
                            // 이동 순서를 Home -> Office -> Playground 라 하자
                            // Home에서 Office로 이동하면 Home이 스택에서 사라짐
                            // (현재 스택) Office
                            // 여기서 Playground로 이동하면 현재 스택에는 Home이 없기 때문에 아무 일도 안 일어남
                            // (현재 스택) Office -> Playground
                            // 여기서 백 버튼을 누르면 Office로 이동됨
                            inclusive = true
                        }
                    }
                }) {
                    Text("Office로 이동")
                }
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Playground로 이동")
                }
                Button(onClick = {
                    navController.navigate("Home") {
                        // 현재 최상단에 해당 요소가 있다면 새로 스택에 추가하지 않음
                        // Home -> Home
                        // (현재 스택) Home
                        // 여기서 백 버튼을 누르면 앱 종료됨
                        launchSingleTop = true
                    }
                }) {
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Argument/MyId")
                }) {
                    Text("아이디로 연결")
                }
            }
        }
        composable("Office") {
            Column {
                Text("Office")
                Button(onClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Playground로 이동")
                }
            }
        }
        composable("Playground") {
            Column {
                Text("Playground")
                Button(onClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Office") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Office로 이동")
                }
            }
        }
        // Argument/{userId}를 route로 받는 composable 생성
        composable("Argument/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.get("userId")
            Text(text = "userId : $userId")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationExamplePreview() {
    Chapter5_plusTheme {
        NavigationExample()
    }
}