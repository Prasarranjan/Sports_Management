$(document).ready(function () {
    // Club change handler
    $('#clubName').change(function () {
        var clubId = $(this).val();
        $('#clubId').val(clubId);

        $.ajax({
            url: 'servlet',
            type: 'POST',
            dataType: 'json',
            data: {
                clubId: clubId,
                event: 'sports'
            },
            success: function (response) {
                var s = '<option value="">Select Sports</option>';

                // Populate sports dropdown
                for (var key in response) {
                    if (response.hasOwnProperty(key)) {
                        s += '<option value="' + response[key].sportsId + '">' + response[key].sportsName + '</option>';
                    }
                }

                $('#sportsName').html(s);
            },
            error: function () {
                alert('No Data Available');
            }
        });
    });

    // Validate applicant name
    $('#applicantName').on('input', function () {
        var name = $(this).val();
        if (name.length <= 3) {
            $('#nameError').text('* Enter a valid name').css('color', 'red');
        } else {
            $('#nameError').text(''); // Clear the error message if valid
        }
    });

    // Validate email
    $('#email').on('input', function () {
        $('#emailError').text(''); // Clear any previous error messages

        const email = $('#email').val().trim();
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

        if (!emailPattern.test(email)) {
            $('#emailError').text('*Enter a valid email').css('color', 'red');
        } else {
            $('#emailError').text(''); // Clear the error message if valid
        }
    });

    // Validate mobile number
    $('#mobileNo').on('input', function () {
        $('#mobileError').text(''); // Clear any previous error messages

        const mobile = $(this).val().trim();
        const mobilePattern = /^[0-9]{10}$/;

        if (!mobilePattern.test(mobile)) {
            $('#mobileError').text('*Enter a valid 10-digit mobile number').css('color', 'red');
        } else {
            $('#mobileError').text(''); // Clear the error message if valid
        }
    });

    // Validate sports dropdown
    $('#sportsName').change(function () {
        var selectedSportsId = $(this).val();
        $('#sportsId').val(selectedSportsId);

        if (selectedSportsId === '') {
            $('#sportsError').text('*Select a sport').css('color', 'red');
        } else {
            $('#sportsError').text(''); // Clear the error message if valid
        }
    });

    // Form submission handler
    $('#playername').submit(function (event) {
        event.preventDefault();

        // Initialize isValid flag
        var isValid = true;

        // Validate name
        var name = $('#applicantName').val();
        if (name.length <= 3) {
            $('#nameError').text('* Enter a valid name').css('color', 'red');
            isValid = false;
        } else {
            $('#nameError').text('');
        }

        // Validate email
        var email = $('#email').val().trim();
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(email)) {
            $('#emailError').text('*Enter a valid email').css('color', 'red');
            isValid = false;
        } else {
            $('#emailError').text('');
        }

        // Validate mobile number
        var mobile = $('#mobileNo').val().trim();
        const mobilePattern = /^[0-9]{10}$/;
        if (!mobilePattern.test(mobile)) {
            $('#mobileError').text('*Enter a valid 10-digit mobile number').css('color', 'red');
            isValid = false;
        } else {
            $('#mobileError').text('');
        }

        // // Validate club selection
        // var clubId = $('#clubName').val();
        // if (clubId === '') {
        //     $('#clubError').text('*Select a club').css('color', 'red');
        //     isValid = false;
        // } else {
        //     $('#clubError').text('');
        // }
        //
        // // Validate sports selection
        // var sportsId = $('#sportsName').val();
        // if (sportsId === '') {
        //     $('#sportsError').text('*Select a sport').css('color', 'red');
        //     isValid = false;
        // } else {
        //     $('#sportsError').text('');
        // }

        // If the form is valid, proceed with the AJAX submission
        if (isValid) {
            var formData = new FormData(this);
            formData.append('event', 'player');
            formData.append('clubId', clubId);
            formData.append('sportsId', sportsId);

            $.ajax({
                url: 'servlet',
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    if (response.trim() === "done") {
                       // alert('Added successfully');
                         $.toast({
                            text: "Book Added Successfully",
                            heading: 'Success',
                            icon: 'success',
                            showHideTransition: 'fade',
                            allowToastClose: true,
                            hideAfter: 5000,
                            stack: 5,
                            position: 'top-center',
                            textAlign: 'left',
                            loader: true,
                            loaderBg: '#9EC600',
                            bgColor: '#28a745'
                        });
                    } else {
                        alert('Failed to add player or photo');
                    }
                },
                error: function () {
                    alert('Error in form submission.');
                }
            });
        }
    });
});
//when club not selected

$(document).ready(function () {

    $.ajax({
        url: "servlet",
        type: "POST",
        data: {
            "event": "viewplayerall",
        },
        dataType: 'JSON',
        success: function (response) {
            let s = "";
            let i = 1;

            // Check if response contains data
            if (response.length > 0) {
                // Loop through each player data and append to the table
                for (var key in response) {
                    if (response.hasOwnProperty(key)) {
                        s += "<tr>";
                        s += "<td>" + response[key].regId + "</td>";
                        s += "<td>" + response[key].name + "</td>";
                        s += "<td>" + response[key].email + "</td>";
                        s += "<td>" + response[key].mobile + "</td>";
                        s+="<td><img src='image/"+response[key].image+"' height='50px' width='50px'></td>";
                        s += "<td>" + response[key].clubName + "</td>";
                        s += "<td>" + response[key].sportsName + "</td>";
                        s += "<td>" + response[key].fees + "</td>";
                        s += "</tr>";
                    }
                    i++;
                }
            } else {
                // If no players found, show a message
                s = "<tr><td colspan='8'>No players found for the selected club</td></tr>";
            }

            // Update the table body with the new rows
            $('#viewplayer').html(s);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error: " + textStatus, errorThrown);
        }
    });
});


$(document).ready(function () {
    $('#clubFilter').change(function () {
        let clubId = $(this).val(); // Get selected clubId

        $.ajax({
            url: "servlet",
            type: "POST",
            data: {
                "event": "viewplayer",
                "ClubId": clubId
            },
            dataType: 'JSON',
            success: function (response) {
                let s = "";
                let i = 1;

                // Check if response contains data
                if (response.length > 0) {
                    // Loop through each player data and append to the table
                    for (var key in response) {
                        if (response.hasOwnProperty(key)) {
                            s += "<tr>";
                            s += "<td>" + response[key].regId + "</td>";
                            s += "<td>" + response[key].name + "</td>";
                            s += "<td>" + response[key].email + "</td>";
                            s += "<td>" + response[key].mobile + "</td>";
                            s+="<td><img src='image/"+response[key].image+"' height='50px' width='50px'></td>";
                            s += "<td>" + response[key].clubName + "</td>";
                            s += "<td>" + response[key].sportsName + "</td>";
                            s += "<td>" + response[key].fees + "</td>";
                            s += "</tr>";
                        }
                        i++;
                    }
                } else {
                    // If no players found, show a message
                    s = "<tr><td colspan='8'>No players found for the selected club</td></tr>";
                }

                // Update the table body with the new rows
                $('#viewplayer').html(s);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error("Error: " + textStatus, errorThrown);
            }
        });
    });
});