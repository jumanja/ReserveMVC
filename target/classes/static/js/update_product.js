window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del producto
    const formulario = document.querySelector('#update_product_form');

    formulario.addEventListener('submit', function (event) {
        let productId = document.querySelector('#product_id').value;

        //creamos un JSON que tendrá los datos del producto
        //a diferencia de un producto nuevo en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#product_id').value,
            registration: document.querySelector('#registration').value,
            name: document.querySelector('#name').value,
            lastName: document.querySelector('#lastName').value,

        };

        //invocamos utilizando la función fetch la API productos con el método PUT que modificará
        //el producto que enviaremos en formato JSON
        const url = '/productos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

//Es la funcion que se invoca cuando se hace click sobre el id de un producto del listado
//se encarga de llenar el formulario con los datos del producto
//que se desea modificar
function findBy(id) {
          const url = '/productos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let product = data;
              document.querySelector('#product_id').value = product.id;
              document.querySelector('#registration').value = product.registration;
              document.querySelector('#name').value = product.name;
              document.querySelector('#lastName').value = product.lastName;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_product_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }