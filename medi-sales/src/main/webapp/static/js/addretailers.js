function validateForm() {
            let isValid = true;
            document.querySelectorAll(".error").forEach(el => el.innerText = "");

            const retailerName = document.getElementById("retailerName").value;
            if (retailerName.length < 3 || retailerName.length > 15) {
                document.getElementById("retailerNameError").innerText = "Name must be between 3 and 15 letters.";
                isValid = false;
            }

            const retailerCompanyName = document.getElementById("retailerCompanyName").value;
            if (retailerCompanyName.length < 3 || retailerCompanyName.length > 15) {
                document.getElementById("retailerCompanyNameError").innerText = "Company Name must be between 3 and 15 letters.";
                isValid = false;
            }

            const retailerPhone = document.getElementById("retailerPhone").value;
            if (!/^[0-9]{10}$/.test(retailerPhone)) {
                document.getElementById("retailerPhoneError").innerText = "Enter a valid 10-digit phone number.";
                isValid = false;
            }

            const retailerEmail = document.getElementById("retailerEmail").value;
            if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(retailerEmail)) {
                document.getElementById("retailerEmailError").innerText = "Enter a valid email address.";
                isValid = false;
            }

            const retailerGstNumber = document.getElementById("retailerGstNumber").value;
            if (retailerGstNumber.length !== 15) {
                document.getElementById("retailerGstNumberError").innerText = "GST number must be 15 characters long.";
                isValid = false;
            }

            const retailerAddress = document.getElementById("retailerAddress").value;
            if (retailerAddress.length < 3 || retailerAddress.length > 50) {
                document.getElementById("retailerAddressError").innerText = "Address must be between 3 and 50 characters.";
                isValid = false;
            }

            return isValid;
        }