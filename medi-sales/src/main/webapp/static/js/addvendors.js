function validateForm() {
            let isValid = true;
            document.querySelectorAll(".error").forEach(el => el.innerText = "");

            const vendorName = document.getElementById("vendorName").value;
            if (vendorName.length < 3 || vendorName.length > 15) {
                document.getElementById("vendorNameError").innerText = "Name must be between 3 and 15 letters.";
                isValid = false;
            }

            const vendorCompanyName = document.getElementById("vendorCompanyName").value;
            if (vendorCompanyName.length < 3 || vendorCompanyName.length > 15) {
                document.getElementById("vendorCompanyError").innerText = "Company Name must be between 3 and 15 letters.";
                isValid = false;
            }

            const vendorPhone = document.getElementById("vendorPhone").value;
            if (!/^[0-9]{10}$/.test(vendorPhone)) {
                document.getElementById("vendorPhoneError").innerText = "Enter a valid 10-digit phone number.";
                isValid = false;
            }

            const vendorEmail = document.getElementById("vendorEmail").value;
            if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(vendorEmail)) {
                document.getElementById("vendorEmailError").innerText = "Enter a valid email address.";
                isValid = false;
            }

            const vendorGstNumber = document.getElementById("vendorGstNumber").value;
            if (vendorGstNumber.length !== 15) {
                document.getElementById("vendorGstNumberError").innerText = "GST number must be 15 characters long.";
                isValid = false;
            }

            const vendorAddress = document.getElementById("vendorAddress").value;
            if (vendorAddress.length < 3 || vendorAddress.length > 50) {
                document.getElementById("vendorAddressError").innerText = "Address must be between 3 and 50 characters.";
                isValid = false;
            }

            return isValid;
        }