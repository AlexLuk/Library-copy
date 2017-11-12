$(document).ready(function () {
    var statusField = $('#status_message');
    var contentField = $('.content_result');

    function getFormArray(elem, msgField) {
        // serializing all elements of the form
        var form_data = elem.closest('form').serializeArray();

        // adding button to the array of form fields
        form_data.push({name: elem.attr('name')});

        if (has_empty_fields(form_data, msgField))
            return '';
        return form_data;
    }

    function has_empty_fields(data, msgField) {
        var res = false;
        $.each(data, function (i, field) {
            //alert( field.name + ' ' + field.value + ' ' + field.type );
            if (field.value === '' && field.type !== 'submit') {
                show_alert("Please fill in all the fields!", msgField, false);
                res = true;
                return false;
            }
        });
        return res;
    }

    function show_alert(msg, msgField, isSuccess) {
        if (isSuccess)
            msgField.removeClass('bg-danger').addClass('bg-success');
        else
            msgField.removeClass('bg-success').addClass('bg-danger');
        msgField.toggle(true).text(msg);
    }

    function showResponse(resp, msgField) {
        if (resp === "false")
            show_alert("Unsuccessful attempt. Please try once again.", msgField, false);
        else
            show_alert(resp, msgField, true);
    }

    function hideMsgs() {
        statusField.toggle(false);
        contentField.toggle(false);
    }

    function prepareContentField() {
        contentField.toggle(true);

        var contentBody = contentField.find('tbody');
        contentBody.empty();
        return contentBody;
    }

    /************************************************* general settings *************************************************/

    // stop submit requests
    $('.doNotProcess').submit(function (e) {
        e.preventDefault();
    });

    $('button').click(function () {
        hideMsgs();
    });

    // hide error message when switching between tabs
    $('.navbar-nav a, #navbar').click(function () {
        hideMsgs();
    });

    $.ajaxSetup(
        {
            type: "POST"
        });

    /********************************************* registration form **************************************************/

    $('#registerForm').validate({
        rules: {
            firstName: "required",
            lastName: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                pwdcheck: true,
                minlength: 8
            }
        },
        messages: {
            firstName: $('#error_firstname').html(),
            lastName: $('#error_lastname').html(),
            password: {
                required: $('#error_password').html(),
                pwdcheck: $('#error_pwd_check').html(),
                minlength: $('#error_pwd_minlen').html()
            },
            email: {
                required: $('#error_email_req').html(),
                email: $('#error_email').html()
            }
        }
    });

    $.validator.addMethod("pwdcheck",
        function (value, element) {
            return /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\S+$).{8,}$/.test(value);
        });

    $('#register').click(function () {
        hideMsgs();
        if ($('#registerForm').valid()) {
            if (checkPassword()) {
                if (provideRegistration()) {
                    location.href = "account";
                }
            }
        }
    });

    $('#email').blur(function () {
        var userEmail = $.trim($('#email').val());
        if (isEmail(userEmail)) {
            $.ajax(
                {
                    url: "/checks/email",
                    data: {email: userEmail},
                    success: function (resp) {
                        if (!resp) {
                            show_alert($('#error_email_not_unique').html(), statusField, false);
                        }
                        else {
                            hideMsgs();
                        }
                    }
                });
        }
    });

    function provideRegistration() {
        var result = false;
        var form_data = $('#registerForm').serialize();
        if (form_data !== '') {
            $.ajax(
                {

                    url: "/register",
                    data: form_data,
                    async: false,
                    success: function (resp) {
                        if (resp) {
                            show_alert($('#succ_register').html(), statusField, true);
                        }
                        result = resp;
                    }
                });
            return result;
        }
        else
            return result;
    }


    function checkPassword() {
        var userEmail = $.trim($('#email').val());
        var userPas = $.trim($('#password').val());
        var result = false;
        if (userEmail !== '' && userPas !== '') {
            $.ajax(
                {
                    url: "/checks/password",
                    data: {password: userPas, email: userEmail},
                    async: false,
                    success: function (resp) {
                        if (!resp) {
                            show_alert($('#error_contains_parts').html(), statusField, false);
                        }
                        result = resp;
                    }
                });
            return result;
        }
        else
            return false;
    }


    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }


    /********************************************* profile changing form **************************************************/

    $('#profileForm').validate({
        rules: {
            firstName: "required",
            lastName: "required",
            changePassword: {
                required: false,
                pwdchange: true,
                minlength: 8
            }
        },
        messages: {
            firstName: $('#error_firstname').html(),
            lastName: $('#error_lastname').html(),
            changePassword: {
                pwdchange: $('#error_pwd_check').html(),
                minlength:  $('#error_pwd_minlen').html()
            }
        }
    });

    $.validator.addMethod("pwdchange",
        function (value, element) {
            return value === '' || /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\S+$).{8,}$/.test(value);
        });

    $('#saveProfile').click(function () {
        console.log("click");
        hideMsgs();
        if ($('#profileForm').valid()) {
            if ($.trim($('#changePassword').val()) === '' || checkChangePassword()) {
                if (changeProfile()) {
                    $('#profileForm').hide();
                }
            }
        }
    });


    function changeProfile() {
        var result = false;
        var form_data = $('#profileForm').serialize();
        var patronymic = $.trim($('#patronymic').val());
        if (form_data !== '') {
            $.ajax(
                {

                    url: "/change_profile",
                    data: {
                        email: $.trim($('#changeEmail').val()),
                        password: $.trim($('#changePassword').val()),
                        lastName: $.trim($('#lastName').val()),
                        firstName: $.trim($('#firstName').val()),
                        patronymic: patronymic
                    },
                    async: false,
                    success: function (resp) {
                        if (resp) {
                            alert("Information was successfully changed");
                        }
                        result = resp;
                    }
                });
            return result;
        }
        else
            return result;
    }


    function checkChangePassword() {
        var userEmail = $.trim($('#changeEmail').val());
        var userPas = $.trim($('#changePassword').val());
        var result = false;
        $.ajax(
            {
                url: "/checks/password",
                data: {password: userPas, email: userEmail},
                async: false,
                success: function (resp) {
                    if (!resp) {
                        show_alert($('#error_contains_parts').html(), statusField, false);
                    }
                    result = resp;
                }
            });
        return result;
    }


    /************************* book filters *****************************************/
    $('#filer_button').click(function () {
        filterRequest();
    });

    function filterRequest() {
        var titleFilter = $.trim($('#book_title').val());
        var authorFilter = $.trim($('#book_author').val());
        var yearFilter = $.trim($('#book_year').val());
        var genreFilter = $("#book_genre option").filter(":selected").attr('id');
        $.ajax(
            {
                url: "/filters",
                data: {title: titleFilter, author: authorFilter, year: yearFilter, genre: genreFilter},
                dataType: "json",
                success: function (resp) {
                    var contentBody = $('.content_res_book');
                    contentBody.empty();

                    $.each(resp, function (key, data) {
                        var onHands = $('#orderOnHandsForm').clone();
                        var inLib = $('#orderInLibForm').clone();

                        var authorList = '';
                        for (var i = 0; i < data.authors.length; i++) {
                            authorList += data.authors[i];
                            if (i > 0) authorList += '</br>';
                        }

                        var htmlContent = '';
                        htmlContent +=
                            '<tr><td>' + data.title + '</td>' +
                            '<td>' + authorList + '</td>' +
                            '<td>' + data.year + '</td>' +
                            '<td>' + data.genre + '</td>';

                        var button = onHands.find('button');
                        addId(button, 'name', data.book_id);
                        addId(button, 'id', data.book_id);

                        button = inLib.find('button');
                        addId(button, 'name', data.book_id);
                        addId(button, 'id', data.book_id);

                        htmlContent += '<td>' + onHands.html() + '</td>';
                        htmlContent += '<td>' + inLib.html() + '</td></tr>';

                        contentBody.append($(htmlContent));
                    });
                }
            });
    }

    function addId(obj, attrName, id) {
        obj.attr(attrName, (obj.attr(attrName) + '_' + id));
    }

    function getId(attrName) {
        var parts = attrName.split("_");
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

    /********************************************* delivery **********************************************/

    $(".orderHands").click(function () {
        var id = getId($(this).attr("name"));
        processOrder(id, true);
    });

    $(".orderLib").click(function () {
        var id = getId($(this).attr("name"));
        processOrder(id, false);
    });

    function processOrder(id, onHands) {
        $.ajax(
            {
                url: "/addOrder",
                data: {bookId: id, toHand: onHands},
                success: function (resp) {
                    if (resp) {
                        alert($('#succ_order_created').html());
                    }
                    else if (!resp) {
                        alert($('#error_order_create').html());
                    }
                }
            });
    }

    /******************************************** profile editing *****************************************/
    $('.deleteReader').click(function () {
        var id = getId($(this).attr("name"));
        console.log(id);
        $.ajax(
            {
                url: "/deleteReader",
                data: {readerId: id},
                async: false,
                success: function (resp) {
                    if (!resp) {
                        // show_alert($('#error_delete_account').html(), statusField, false);
                        alert($('#error_delete_account').html());
                    }
                    else {
                        // show_alert($('#succ_account_deleted').html(), statusField, true);
                        alert($('#succ_account_deleted').html());
                    }
                }
            });
    });
});