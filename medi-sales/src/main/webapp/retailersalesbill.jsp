<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Sales Bill</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
  <style>
          /* General Styling */
          body {
              font-family: 'Segoe UI', Arial, sans-serif;

              background-size: cover;
              color: #333;
              margin: 0;
              padding: 0;
          }
          .container {
              max-width: 850px;
              margin: 30px auto;
               background-color: rgba(255, 255, 255, 0.5);
              border-radius: 10px;
              box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
              padding: 30px;
          }
          /* Header Styling */
          .header {
              background-color: #0056b3;
              padding: 15px 30px;
              display: flex;
              justify-content: space-between;
              align-items: center;
              color: white;
          }
          .header h1 {
              font-size: 24px;
              margin: 0;
          }
          .header .home-btn {
              background-color: #fff;
              color: #0056b3;
              padding: 10px 20px;
              font-size: 14px;
              font-weight: bold;
              border-radius: 5px;
              cursor: pointer;
              text-decoration: none;
          }
          .header .home-btn:hover {
              background-color: #0056b3;
              color: white;
              border: 2px solid white;
          }

          /* Footer Styling */
          footer {
              background-color: #f5f9fa;
              color: #333;
              text-align: center;
              padding: 10px 20px;
              position: fixed;
              bottom: 0;
              width: 100%;
              box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
          }
          footer p {
              margin: 0;
              font-size: 14px;
          }

          /* Form Styling */
          h2 {
              margin-bottom: 20px;
              color: #0056b3;
          }

          .form-row {
              margin-top: 20px;
          }

          .form-row input, .form-row select {
              margin-bottom: 10px;
          }

          .form-btn {
              background-color: #0056b3;
              color: white;
              padding: 12px 20px;
              border: none;
              border-radius: 5px;
              cursor: pointer;
              width: 100%;
          }

          .form-btn:hover {
              background-color: #003d80;
          }

          /* Table Styling */
          #sales-table {
              width: 100%;
              border-collapse: collapse;
              margin-top: 30px;
              background-color: #fff;
              box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
          }
          #sales-table th, #sales-table td {
              padding: 12px;
              text-align: left;
              border-bottom: 1px solid #ddd;
          }
          #sales-table th {
              background-color: #f5f9fa;
              font-weight: bold;
          }

          #maxStockMessage {
              font-size: 12px;
              color: blue;
              margin-top: 5px;
          }

          .col-md-3, .col-md-2 {
              padding-right: 10px;
          }

          @media (max-width: 768px) {
              .container {
                  padding: 20px;
              }

              .form-row {
                  flex-direction: column;
              }

              .form-btn {
                  width: auto;
                  background-color:#ff4500;
              }
          }
      </style>
  </head>
  <body>

      <div class="container">

          <form method="post" action="retailersalesbill">
              <h2 id="page-title">Reatailer Sales Bill</h2>


              <div class="row form-row">
                  <div class="col-md-3">
                      <select name="productName" id="productName" class="form-select" required onchange="fetchProductStock()">
                          <option value="">Select Product</option>
                      </select>
                  </div>
                  <div class="col-md-3">
                      <select name="contactPerson" id="contactPerson" class="form-select" required>
                          <option value="">Select Retailer</option>
                      </select>
                  </div>
                  <div class="col-md-2">
                      <input type="number" name="quantity" id="quantityEntered" class="form-control" placeholder="Quantity" required  oninput="calculateBill()" />
                      <div id="maxStockMessage"></div>
                  </div>
                  <div class="col-md-2">
                      <input type="number" name="gst" id="gstEntered" class="form-control" placeholder="GST(%)" required oninput="calculateBill()"  />
                  </div>
                  <div class="col-md-2">
                      <input type="number" name="discount" id="discountEntered" class="form-control" placeholder="Discount(%)" required oninput="calculateBill()" />
                  </div>
                  <input type="hidden" id="calculatedAmount" value="0">

              </div>

              <!-- Submit Button -->
              <div class="form-row mt-3">
                  <button type="submit" class="form-btn">Add</button>

              </div>
          </form>

        <h3 class="mt-4">Bill Details</h3>
        <div id="sales-rows">
            <div class="row mb-2">
                <!-- Hidden Sl. No -->
                <div class="col-12 col-md-4" style="display: none;">
                    <strong>Sl. No:</strong>
                </div>
                <div class="col-12 col-md-4">
                    <strong>Bill No:</strong> ${billNumber} <!-- Dynamically fetched Bill Number -->
                </div>
                <div class="col-12 col-md-4">
                    <strong>Bill Date:</strong> ${billDate} <!-- Dynamically fetched Bill Date -->
                </div>
            </div>
            <!-- You can add more rows if necessary based on your logic -->
        </div>

                 <h3 class="mt-4">Product Details</h3>
                 <table id="product-table" class="table table-striped">
                     <thead>
                         <tr>
                             <th>Sl. No</th>
                             <th>Product Name</th>
                             <th>Product Company</th>
                             <th>HSN Number</th>
                             <th>Batch Number</th>
                             <th>MFG Date</th>
                             <th>EXP Date</th>
                             <th>MRP</th>
                             <th>Rate</th>
                             <th>Quantity</th>
                             <th>GST (%)</th>
                             <th>Discount (%)</th>
                             <th>Amount</th>
                         </tr>
                     </thead>
                     <tbody>
                         <c:forEach var="dto" items="${dtoList}" varStatus="status">
                             <tr>
                                 <td>${status.index + 1}</td>
                                 <td>${dto.productName}</td>
                                 <td>${dto.productCompany}</td>
                                 <td>${dto.hsn}</td>
                                 <td>${dto.batchNumber}</td>
                                 <td>${dto.mfgDate}</td>
                                 <td>${dto.expDate}</td>
                                 <td>${dto.mrp}</td>
                                 <td>${dto.rate}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.gst}</td>
                                <td>${dto.discount}</td>
                                <td>${dto.amount}</td>

                             </tr>
                         </c:forEach>
                     </tbody>
                 </table>

                 <!-- Total Amount -->
                <h4 class="mt-4 text-end" id="allTotal">Total: ${total}</h4>

             </div>
             <div>
               <a href="http://localhost:2992/medi-sales/generatePdf">Generate Bill</a>
               </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            fetchProducts();
            fetchVendors();
            document.querySelectorAll(".quantity, .gst, .discount").forEach(function(element) {
                element.addEventListener("input", function() {
                    let index = this.id.replace(/\D/g, ''); // Extract index number from element ID
                    updateAmount(index);
                });
            });
        });

        function fetchProducts() {
            fetch('http://localhost:2992/medi-sales/api/getProducts')
                .then(response => response.json())
                .then(data => {
                    let selectElement = document.getElementById("productName");
                    selectElement.innerHTML = '<option value="">Select Product</option>';
                    if (data.length > 0) {
                        data.forEach(product => {
                            let option = document.createElement("option");
                            option.value = product;
                            option.text = product;
                            option.setAttribute("rate", product);
                            selectElement.appendChild(option);
                        });
                    }
                })
                .catch(error => console.error('Error fetching products:', error));
        }

        function fetchVendors() {
            fetch("http://localhost:2992/medi-sales/api/getContactPersons")
                .then(response => response.json())
                .then(data => {
                    let selectElement = document.getElementById("contactPerson");
                    selectElement.innerHTML = '<option value="">Select Vendor</option>';
                    if (data.length > 0) {
                        data.forEach(vendor => {
                            let option = document.createElement("option");
                            option.value = vendor;
                            option.text = vendor;
                            selectElement.appendChild(option);
                        });
                    }
                })
                .catch(error => console.error("Error fetching vendors:", error));
        }

        function fetchProductStock() {
            const name = document.getElementById("productName").value;
            console.log(name);
            if (name) {
                fetch("http://localhost:2992/medi-sales/api/getStockByProduct?productName=" + name)
                    .then(response => response.json())
                    .then(stock => {
                        const maxStockMessage = document.getElementById("maxStockMessage");
                        maxStockMessage.textContent = "Maximum quantity available: " + stock;

                        const quantityInput = document.getElementById("quantityEntered");
                        quantityInput.setAttribute("placeholder", "Enter quantity (max " + stock + ")");
                    })
                    .catch(error => console.error('Error fetching product stock:', error));
            } else {
                document.getElementById("maxStockMessage").textContent = '';
                document.getElementById("quantityEntered").setAttribute("placeholder", "Enter quantity");
            }
        }



      function calculateBill() {
          let rate = parseFloat(document.getElementById("productName").selectedOptions[0]?.getAttribute("data-rate")) || 0;
          let quantity = parseFloat(document.getElementById("quantityEntered").value) || 0;
          let gst = parseFloat(document.getElementById("gstEntered").value) || 0;
          let discount = parseFloat(document.getElementById("discountEntered").value) || 0;

          if (quantity <= 0 || rate <= 0) {
              updateTotalAmount();
              return;
          }

          let subtotal = rate * quantity;
          let discountAmount = (subtotal * discount) / 100;
          let priceAfterDiscount = subtotal - discountAmount;
          let gstAmount = (priceAfterDiscount * gst) / 100;
          let finalAmount = priceAfterDiscount + gstAmount;

          let calculatedAmountField = document.getElementById("calculatedAmount");
          if (calculatedAmountField) { // Check if it exists
              calculatedAmountField.value = finalAmount.toFixed(2);
          }

          updateTotalAmount();
      }

    function updateTotalAmount() {
        let calculatedAmountField = document.getElementById("calculatedAmount");

        if (!calculatedAmountField) {
            console.error("Element with ID 'calculatedAmount' not found!");
            return;
        }

        let total = parseFloat(calculatedAmountField.value) || 0;
        document.getElementById("allTotal").textContent = "Total: " + total.toFixed(2);
    }



    </script>


</body>
</html>
