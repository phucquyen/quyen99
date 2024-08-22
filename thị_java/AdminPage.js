// Khai báo
listProduct = [];
// listManufacturer=[];
listCategory=[];

//khai bao cac bien luu page va size hien tai
var curentPage=1;
var curentsize=5;

//khai bao cac bien dung trong sort du lieu
var sortField="id";
var isAsc=true;

// Khai báo biến lưu dữ liệu Search
var search = "";
//

var idProductUpdate = "";
$(function () {
  loadComponentAdmin();
});

// Khai báo biến lưu Username và Password, dữ liệu lấy từ LocalStorage
var accountLoginLocalStorage = JSON.parse(localStorage.getItem("accountLogin"));
var usernameLogin = accountLoginLocalStorage.userName;
var passWordLogin = accountLoginLocalStorage.passWord;


// Load các thành phần của trang Home Page
function loadComponentAdmin(params) {
  $(".MenuSection").load("./MenuAdmin.html");
  $(".SideBarSection").load("./SideBarAdmin.html");
}

// Xử lý khi click vào menu Product
function handleShowProduct(params) {
  // Load nội dung ContentProductAdmin
  $(".ProductAdminSection").load("./ContentProductAdmin.html", "data", function (response, status, request) {
    // Sau khi load thành công giao diện mới thực thi các hàm Callback trong này.
    fetchListProductAdmin();
    //Load du lieu Manufacturer cho Modal Form tao moi Product
    // fetchListManufacturerAdmin();
    //Load du lieu Category cho Modal From tao moi Product
    fetchListCategoryAdmin();
    // Disable trường Id do không cần dùng nữa
    $("#Id").attr("disabled", "disabled");

  });
}

// Xử lý khi click vào menu Manufacturer
// function handleShowManufacturer(params) {
//   // Load nội dung ContentManufacturerAdmin
//   $(".ProductAdminSection").load("./ContentManufacturerAdmin.html", "data", function (response, status, request) {});
// }

// Xử lý khi click vào menu Category
function handleShowCategory(params) {
  // Load nội dung ContentCategoryAdmin
  $(".ProductAdminSection").load("./ContentCategoryAdmin.html", "data", function (response, status, request) {});
}

// Xử lý khi click vào menu Account
function handleShowAccount(params) {
  // Load nội dung ContentAccountAdmin
  $(".ProductAdminSection").load("./ContentAccountAdmin.html", "data", function (response, status, request) {});
}

// hàm xử lý thêm mới Product
function handleCreateNewProduct(params) {
  // alert("Create New!!");
  // Lấy dữ liệu từ các ô Input
//   var v_Id = $("#Id").val();
  var v_Name = $("#Name").val();
  var v_Price = $("#Price").val();
  var v_Info = $("#Info").val();
  var v_Detail = $("#Detail").val();
  var v_Star = $("#Star").val();
  // Gọi hàm để lấy tên Ảnh
  var v_Image = getImageName($("#Image").val());
//   var v_Manufacturer = $("#Manufacturer").val();
  var v_Category = $("#Category").val();

  // Tạo đối tượng ProductNew để lưu trữ
  var ProductNew = {
    // id: v_Id,
    name: v_Name,
    price: v_Price,
    info: v_Info,
    detail: v_Detail,
    ratingStar: v_Star,
    imageName: v_Image,
    // manufacturerId: v_Manufacturer,
    categoryId: v_Category,
  };
  // console.log("ProductNew: ", ProductNew);

  // Add thêm sản phẩm vào listProduct
//   listProduct.push(ProductNew);
  // Lưu dữ liệu localStorage
//   localStorage.setItem("listProduct", JSON.stringify(listProduct));

// Thực hiện Call API tạo mới Product
$.ajax({
    type: "POST",
    url: "http://localhost:8080/api/v1/products",
    data: JSON.stringify(ProductNew),
    // dataType: "POST",
    contentType: "application/json; charset=UTF-8",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa(`${usernameLogin}:${passWordLogin}`));
    },

    success: function (response, status) {
      // console.log("response: ", response);
      // console.log("status: ", status);


      if (status === "success") {
        fetchListProductAdmin();
      } else {
        console.log("Error when loading data!!");
        return;
      }
    },
  });

  // Thực hiện Reset Form
  handleResetForm();
  // Gọi hàm hiển thị lại danh sách sản phẩm
  fetchListProductAdmin();
}

// Hàm Load dữ liệu Product, sau đó đổ dữ liệu vào Table
function fetchListProductAdmin(params) {
  // Reset lại listProduct về Null
  listProduct = [];

  
  var  v_url="";
  //thuc hien phan trang,sort du lieu
  
    var  v_url=`http://localhost:8080/api/v1/products?page=${curentPage}&size=${curentsize}&search=${search}`;
    

  //Lấy dữ liệu từ LocalStorage để sử dụng
  // Kiểm tra xem có dữ liệu dưới LocalStorage không
  // if (localStorage && localStorage.getItem("listProduct")) {
  //   var listProductLocalStorage = JSON.parse(localStorage.getItem("listProduct"));
  //   // Lưu dữ liệu từ localStorage vào listProduct trong JS để sử dụng
  //   listProduct = listProductLocalStorage;
  // }
  //Thuc hien Call API
  $.ajax({
    type: "GET",
    // contentType: "application/json; charset=utf-8",
    dataType: "json",
    url: v_url,
    // data: "data",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa(`${usernameLogin}:${passWordLogin}`));
    },

    success: function (response,status) {
      console.log("response:",response);
      console.log("status:",status);

     if (status==="success") {
      listProduct=response.content;
       //Xóa bảng dữ liệu hiện tại
  $("#tbProductAdmin").empty();
  // Dùng vòng lặp để tạo product
  for (let index = 0; index < listProduct.length; index++) {
    $("#tbProductAdmin").append(`
    <tr>
      <td>${listProduct[index].id}</td>
      <td>${listProduct[index].name}</td>
      <td>${listProduct[index].price}</td>
      <td>${listProduct[index].info}</td>
      <td>${listProduct[index].detail}</td>
      <td>${listProduct[index].ratingStar}</td>
      <td>${listProduct[index].imageName}</td>
      <td>${listProduct[index].categoryName}</td>
      <td>
        <button type="button" class="btn btn-warning" onclick="handleEdit(${listProduct[index].id})">Edit</button>
      </td>
      <td>
        <button type="button" class="btn btn-danger" onclick="handleDelete(${listProduct[index].id})">Delete</button>
      </td>
  </tr>
    `);
  }

      //hien thi cac nut phan trang
      //lay ra totalpages
      var totalPages=response.totalPages;
      // console.log("totalPages:",totalPages);
      //
      pagingTable(totalPages);

     } else {
      console.log("error when loading data");
      return;
     }
    }
  });
  
  // //Xóa bảng dữ liệu hiện tại
  // $("#tbProductAdmin").empty();
  // // Dùng vòng lặp để tạo product
  // for (let index = 0; index < listProduct.length; index++) {
  //   $("#tbProductAdmin").append(`
  //   <tr>
  //     <td>${listProduct[index].id}</td>
  //     <td>${listProduct[index].name}</td>
  //     <td>${listProduct[index].price}</td>
  //     <td>${listProduct[index].info}</td>
  //     <td>${listProduct[index].detail}</td>
  //     <td>${listProduct[index].ratingStar}</td>
  //     <td>${listProduct[index].imageName}</td>
  //     <td>${listProduct[index].manufacturerId}</td>
  //     <td>${listProduct[index].categoryId}</td>
  //     <td>
  //       <button type="button" class="btn btn-warning" onclick="handleEdit(${listProduct[index].id})">Edit</button>
  //     </td>
  //     <td>
  //       <button type="button" class="btn btn-danger" onclick="handleDelete(${listProduct[index].id})">Delete</button>
  //     </td>
  // </tr>
  //   `);
  // }
}

function pagingTable(totalPages_Param){
  $("#pagination_Id").empty();

   //hien thi nut previous
   if (curentPage>1) {
    $("#pagination_Id").append(`
   <li class="page-item"><a href="#" class="page-link" onclick="handlePrevious()">Previous</a></li>
     `);
   }
   

  //hien thi cac nut 1,2,3..
  for (let index = 1; index <= totalPages_Param; index++) {
    if (index=== curentPage) {
      //Active
      $("#pagination_Id").append(`
    <li class="active"><a href="#" onclick="handleChangePage(${index})">${index}</a></li>
    `);
    } else {
      $("#pagination_Id").append(`
    <li><a href="#" onclick="handleChangePage(${index})">${index}</a></li>
    `);
    
    }
  }

  //hien thi nut next
  if (curentPage<totalPages_Param) {
    $("#pagination_Id").append(`
   <li class="page-item"><a href="#" class="page-link" onclick="handleNext()">Next</a></li> 
  `);
  }
}

// viet ham xu ly khi thay doi trang
function handleChangePage(pageParam){
  // kiem tra xem trang co phai trang hien tai hk,neu la trang hien tai thi hk lam j
if (pageParam===curentPage) {
  return;
} else {
  curentPage=pageParam;
  fetchListProductAdmin();
}
}
//viet ham xu ly chi nut previous
function handlePrevious(params){
  curentPage=curentPage - 1;
  fetchListProductAdmin();
}
// viet ham xu ly cho nut next
function handleNext(params){
  curentPage=curentPage + 1;
  fetchListProductAdmin();
}

// Hàm xử lý search dữ liệu
function handleSeach() {
  // console.log("handleSeach");
  var searchParam = $("#searchId").val();
  // console.log("searchParam: ", searchParam);
  // gán giá trị search bằng giá trị người dùng nhập
  search = searchParam.trim();
  // Gọi hàm để hiển thị sản phẩm
  fetchListProductAdmin();
  // Reset lại ô nhập liệu
  $("#searchId").val("");

}
function handleLogout(params) {
  // Xóa thông tin AccountLogin ở localStorage
  localStorage.removeItem("accountLogin");
  //   Redirect tới trang Home
  window.open("./HomePage.html", "_self");
}



// Hàm handleResetForm, xóa dữ liệu trong các ô Input
function handleResetForm() {
  // Gọi lại các Form nhập liệu và reset giá trị
  $("#Id").val("");
  $("#Name").val("");
  $("#Price").val("");
  $("#Info").val("");
  $("#Detail").val("");
  $("#Star").val("");
  $("#Image").val("");
  $("#Manufacturer").val("");
  $("#Category").val("");
}

// Hàm lấy tên ảnh
function getImageName(pathImage) {
  // Chuyển đường dẫn thành mảng các phần tử
  var itemArray = pathImage.split("\\");
  // Lấy phần tử cuối cùng
  var imageName = itemArray[itemArray.length - 1];

  return imageName;
}

// Hàm xóa sản phẩm
function handleDelete(idDelete) {
  // Hiện hộp thoại confirm
  var confirmDelete = confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?");
  if (confirmDelete) {
    // Tìm index của Product cần xóa theo id
    var indexPrductDelete = listProduct.findIndex((product) => product.id == idDelete);
    // Nếu không tìm thấy sản phẩm indexPrductDelete=-1

    if (indexPrductDelete !== -1) {
      // Xóa Product trong listProduct đang lưu ở JS
     //   listProduct.splice(indexPrductDelete, 1);
     //   // Lưu lại listProduct vào LocalStorage
     //   localStorage.setItem("listProduct", JSON.stringify(listProduct));
     var urlDelete = "http://localhost:8080/api/v1/products/" + idDelete;
      $.ajax({
        type: "DELETE",
        url: urlDelete,
        // data: "data",
        // dataType: "dataType",
        beforeSend: function (xhr) {
          xhr.setRequestHeader("Authorization", "Basic " + btoa(`${usernameLogin}:${passWordLogin}`));
        },
    
        success: function (response) {
          // Hiển thị lại dữ liệu
          fetchListProductAdmin();
        },
      });

      // Hiển thị lại dữ liệu
      fetchListProductAdmin();
    } else {
      alert("Không thể xóa sản phẩm");
    }
  }
}

// Hàm xử lý khi click vào nút Edit
function handleEdit(idEditProduct) {
  // Lưu lại id của sản phẩm cần Update để sử dụng cho chức năng Update
  idProductUpdate = idEditProduct;
  // Tìm index của sản phẩm cần Update trong mảng listProduct
  var index = listProduct.findIndex((product) => product.id == idProductUpdate);

  // Tìm categoryId của Product cần Update
  var categoryUpdate = listCategory.find((category) => category.name == listProduct[index].categoryName);

  // Điền thông tin của sản phẩm cần Update vào các ô Input
  // Không điền tên ảnh vào Input File
  $("#IdUpdate").val(listProduct[index].id);
  $("#NameUpdate").val(listProduct[index].name);
  $("#PriceUpdate").val(listProduct[index].price);
  $("#InfoUpdate").val(listProduct[index].info);
  $("#DetailUpdate").val(listProduct[index].detail);
  $("#StarUpdate").val(listProduct[index].ratingStar);
//   $("#ManufacturerUpdate").val(listProduct[index].manufacturerId);
  $("#CategoryUpdate").val(categoryUpdate.id);

  // Hiển thị Modal Update Product
  $("#ModalUpdateProduct").modal("show");
}

// Hàm xử lý Reset trên Form Update
function handleResetUpdate() {
  $("#NameUpdate").val("");
  $("#PriceUpdate").val("");
  $("#InfoUpdate").val("");
  $("#DetailUpdate").val("");
  $("#StarUpdate").val("");
  $("#ImageUpdate").val("");
  $("#ManufacturerUpdate").val(0);
  $("#CategoryUpdate").val(0);
}

// Hàm xử lý khi nhấn nút Update trên Update Modal
function handleUpdateProduct() {
  // idProductUpdate
  // Tìm index của sản phẩm cần Update trong mảng listProduct
  var index = listProduct.findIndex((product) => product.id == idProductUpdate);

  // Lấy dữ liệu từ các ô Input
  var v_Name = $("#NameUpdate").val();
  var v_Price = $("#PriceUpdate").val();
  var v_Info = $("#InfoUpdate").val();
  var v_Detail = $("#DetailUpdate").val();
  var v_Star = $("#StarUpdate").val();
  // Gọi hàm để lấy tên Ảnh
  var v_Image = getImageName($("#ImageUpdate").val());
  var v_Manufacturer = $("#ManufacturerUpdate").val();
  var v_Category = $("#CategoryUpdate").val();

  // console.log("v_Name", v_Name);
  // console.log("v_Price", v_Price);
  // console.log("v_Info", v_Info);
  // console.log("v_Detail", v_Detail);
  // console.log("v_Star", v_Star);
  // console.log("v_Image:", v_Image);
  // console.log("v_Manufacturer", v_Manufacturer);
  // console.log("v_Category", v_Category);
  // Thực hiện Update thông tin Sản phẩm
 //   listProduct[index].name = v_Name;
 //   listProduct[index].price = v_Price;
 //   listProduct[index].info = v_Info;
 //   listProduct[index].detail = v_Detail;
 //   listProduct[index].ratingStar = v_Star;
  // Kiểm tra nếu người dùng chọn lại ảnh thì mới Set dữ liệu mới
  // TH Người dùng không chọn lại ảnh sẽ lấy ảnh hiện tại của sản phẩm
 //   if (v_Image !== null && v_Image !== "") {
 //     listProduct[index].imageName = v_Image;
 //   }
 //   listProduct[index].manufacturerId = v_Manufacturer;
 //   listProduct[index].categoryId = v_Category;

  // Lưu lại dữ liệu vào LocalStorage
 //   localStorage.setItem("listProduct", JSON.stringify(listProduct));
 
 // Call API
  // Kiểm tra xem người dùng có chọn lại ảnh hay không
  // Nếu không chọn lại thì lấy tên ảnh hiện tại
  if (v_Image == null || v_Image == "") {
    v_Image = listProduct[index].imageName;
  }
  // Thực hiện tạo productUpdate
  let productUpdate = {
    name: v_Name,
    price: v_Price,
    info: v_Info,
    detail: v_Detail,
    ratingStar: v_Star,
    imageName: v_Image,
    manufacturerId: v_Manufacturer,
    categoryId: v_Category,
  };
  // console.log("productUpdate: ", productUpdate);
  //
  let urlUpdate = "http://localhost:8080/api/v1/products/" + idProductUpdate;
  $.ajax({
    type: "PUT",
    url: urlUpdate,
    data: JSON.stringify(productUpdate),
    // dataType: "dataType",
    contentType: "application/json; charset=UTF-8",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa(`${usernameLogin}:${passWordLogin}`));
    },

    success: function (response, status) {
     //  console.log("response:", response);
     //  console.log("status:", status);
      // Kiểm tra nếu status= success
      if (status === "success") {
        // Hiển thị lại dữ liệu sau Update
        fetchListProductAdmin();
      } else {
        console.log("Update Not Success!!");
      }
    },
  });


  // Reset Form Update
  handleResetUpdate();

  // Đóng Modal Update
  $("#ModalUpdateProduct").modal("hide");

  // Hiển thị lại dữ liệu sau Update
  fetchListProductAdmin();
}
// //Ham do du lieu vao Select option Manufaturer Modal
// function fetchListManufacturerAdmin(params){
//   //Reset lai listProduct ve Null
//   listManufacturer=[];
//   //Thuc hien Call API
//   $.ajax({
//     type: "GET",
//     url: "http://localhost:8080/api/v1/manufacturers",
//     // data: "data",
//     dataType: "json",
//     success: function (response,status) {
//       // console.log("response:",response);
//       // console.log("status:",status);
//       //kiem tra neu status=success
//       if (status==="success") {
//         listManufacturer=response;
//         //xoa bang du lieu hien tai
//         // $("#Category").empty();
//         //dung vong lap de tao Manufacturer trong o select
//         for (let index = 0; index < listManufacturer.length; index++) {
//           //create
//           ("#Manufacturer").append(`
//           <op$tion value="${listManufacturer[index].id}">${listManufacturer[index].name}</option>
//           `);
//           //update
//           ("#ManufacturerUpdate").append(`
//           <op$tion value="${listManufacturer[index].id}">${listManufacturer[index].name}</option>
//           `);
//         }
//       } else {
//         console.log("error");
//         return;
//       }
//     }
//   });
// }

//ham doi du lieu vao salect option catergory modal
function fetchListCategoryAdmin(params){
  //reset lai listprocuct ve null
  listCategory = [];
  // Thực hiện Call API
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/v1/categorys",
    // data: "data",
    dataType: "json",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa(`${usernameLogin}:${passWordLogin}`));
    },
    
    success: function (response, status) {
      // console.log("response:", response);
      // console.log("status:", status);
      // Kiểm tra nếu status= success
      if (status === "success") {
        listCategory = response;
        //Xóa bảng dữ liệu hiện tại
        // $("#Category").empty();
        // Dùng vòng lặp để tạo Manufacturer trong ô Select
        for (let index = 0; index < listCategory.length; index++) {
          //  Create
          $("#Category").append(`
          <option value="${listCategory[index].id}">${listCategory[index].name}</option>
         `);
          // Update
          $("#CategoryUpdate").append(`
         <option value="${listCategory[index].id}">${listCategory[index].name}</option>
        `);
        }
      } else {
        console.log("Error when loading data!!");
        return;
      }
    },
  });

}