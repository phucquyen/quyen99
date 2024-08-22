//Ham xu ly khi nhan nut Back To HomePage
function handleBackHome(params){
    window.open("./HomePage.html","_self");
}
//Ham xu ly khi nhan nut Login
function handleLogin(){
    //lay thong tin nhap du lieu nguoi dung
    var username = document.getElementById("Username_Login_id").value;
  var password = document.getElementById("Password_Login_id").value;
        console.log("userName: ",username);
        console.log("pass: ",password);
    //gui Requet login---redirect toi trang Account
  $.ajax({
  
    url:  "http://localhost:8080/api/v1/logins",
    // data: "data",
    type: "GET",
    contentType: "application/json",
    dataType: "json",
    async: false,
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
    },
    success: function (response, textStatus, xhr) {
        // console.log("response:"+response);
      // console.log("status:"+status);
      console.log("thanh cong");
      //kiem tra neu status=success
      if(textStatus==="success"){
        //kiem tra xem Account da active chua
        if(response.status!=="NOT ACTIVE"){
            //Tao thong tin Account vua Login
            var accountLogin={
                id: response.id,
            userName: username,
            passWord: password,
            fullName: response.fullName,
            status: response.status,

            };
            //luu Account xuong LocalStorage
            localStorage.setItem("accountLogin",JSON.stringify(accountLogin));
            //redirect toi trang Admin
            window.open("AdminPage.html","_self");
        }else{
            console.log("Not Active");
          alert("Account chưa được Active!");
        }
      } else {
        console.log("Error when loading data!!");
        return;
        }
      },
    });
    
}