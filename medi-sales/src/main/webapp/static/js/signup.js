// Company Name Validation
function forcompanyName() {
    var companyNames = document.getElementById("companyName").value;
    var button = document.getElementById("button");
    if (companyNames.length != "" && companyNames.length >= 3 && companyNames.length <= 10) {
        document.getElementById("validcompanyname").innerHTML = ""; // Clear error message
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validcompanyname").innerHTML = "<span style='color: red;'>Company Name must be 3 to 10 characters</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Contact Person Validation
function forcontactPerson() {
    var contactPersons = document.getElementById("contactPerson").value;
    var button = document.getElementById("button");
    if (contactPersons.length != "" && contactPersons.length >= 3 && contactPersons.length <= 30) {
        document.getElementById("validcontactperson").innerHTML = "";
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validcontactperson").innerHTML = "<span style='color: red;'>Contact Person must be 3 to 30 characters</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Check Email Validation
function checkEmail() {
    var emails = document.getElementById("email").value;
    var button = document.getElementById("button");
    if (emails.length != "" && emails.length >= 3 && emails.length <= 30) {
        document.getElementById("validemail").innerHTML = "";
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validemail").innerHTML = "<span style='color: red;'>Email must be valid</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Check Phone Validation
function checkPhone() {
    var mobiles = document.getElementById("mobile").value;
    var button = document.getElementById("button");
    if (mobiles.length != "" && mobiles.length == 10) {
        document.getElementById("validmobile").innerHTML = "";
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validmobile").innerHTML = "<span style='color: red;'>Phone number must be 10 digits</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Address Validation
function foraddress() {
    var addressss = document.getElementById("address").value;
    var button = document.getElementById("button");
    if (addressss.length != "" && addressss.length >= 3 && addressss.length <= 50) {
        document.getElementById("validaddress").innerHTML = "";
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validaddress").innerHTML = "<span style='color: red;'>Enter the proper address</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Pincode Validation
function forpincode() {
    var pin = document.getElementById("pincode").value;
    var button = document.getElementById("button");
    if (pin.length != "" && pin.length == 6) {
        document.getElementById("validpincode").innerHTML = "";
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validpincode").innerHTML = "<span style='color: red;'>Pincode must be 6 digits</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Password Validation
function forpassword() {
    var pwd = document.getElementById("password").value;
    var button = document.getElementById("button");
    if (pwd.length != "" && pwd.length >= 8 && pwd.length <= 16) {
        document.getElementById("validpassword").innerHTML = "";
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validpassword").innerHTML = "<span style='color: red;'>Password must be min 8 and max 16</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Confirm Password Validation
function forconformpwd() {
    var pwd = document.getElementById("password").value;
    var cpwd = document.getElementById("confirmPassword").value;
    var button = document.getElementById("button");
    if (cpwd.length != "" && cpwd == pwd) {
        document.getElementById("validcpwd").innerHTML = "";
        button.removeAttribute("Disabled");
    } else {
        document.getElementById("validcpwd").innerHTML = "<span style='color: red;'>Confirm password does not match with Password</span>";
        button.setAttribute("Disabled", "");
        return;
    }
}

// Form validation
function form() {
    var companyNames = document.getElementById("companyName").value;
    var contactPersons = document.getElementById("contactPerson").value;
    var emails = document.getElementById("email").value;
    var mobiles = document.getElementById("mobile").value;
    var addressss = document.getElementById("address").value;
    var pin = document.getElementById("pincode").value;
    var pwd = document.getElementById("password").value;
    var cpwd = document.getElementById("confirmPassword").value;
    var button = document.getElementById("button");

    if (
        companyNames.length != "" && companyNames.length >= 3 && companyNames.length <= 10 &&
        contactPersons.length != "" && contactPersons.length >= 3 && contactPersons.length <= 30 &&
        emails.length != "" && emails.length >= 3 && emails.length <= 30 &&
        mobiles.length != "" && mobiles.length == 10 &&
        addressss.length != "" && addressss.length >= 3 && addressss.length <= 50 &&
        pin.length != "" && pin.length == 6 &&
        pwd.length != "" && pwd.length >= 8 && pwd.length <= 16 &&
        cpwd.length != "" && cpwd == pwd
    ) {
        return true; // Allow form submission
    } else {
        button.setAttribute("Disabled", ""); // Disable button
        return false; // Prevent form submission
    }
}