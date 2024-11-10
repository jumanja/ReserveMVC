window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo producto
    const formulario = document.querySelector('#add_new_product');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

       //creamos un JSON que tendrá los datos del nuevo producto
        const formData = {
            registration: document.querySelector('#registration').value,
            name: document.querySelector('#name').value,
            lastName: document.querySelector('#lastName').value,

        };
        //invocamos utilizando la función fetch la API productos con el método POST que guardará
        //el producto que enviaremos en formato JSON
        const url = '/productos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 //Si no hay ningun error se muestra un mensaje diciendo que el producto
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Producto agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que el producto
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otro producto
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#registration').value = "";
        document.querySelector('#name').value = "";
         document.querySelector('#lastName').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/productList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});