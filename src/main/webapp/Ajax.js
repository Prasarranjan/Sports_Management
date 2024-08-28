$(document).ready(function() {
    // Club change handler
    $('#clubName').change(function() {
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
            success: function(response) {
                var s = '<option value="">Select Sports</option>';

                // Populate sports dropdown
                for (var key in response) {
                    if (response.hasOwnProperty(key)) {
                        s += '<option value="' + response[key].sportsId + '">' + response[key].sportsName + '</option>';
                    }
                }

                $('#sportsName').html(s);
            },
            error: function() {
                alert('No Data Available');
            }
        });
    });

    // Sports change handler
    $('#sportsName').change(function() {
        var selectedSportsId = $(this).val();
        $('#sportsId').val(selectedSportsId);
    });

    // Player form submission handler
    $('#playername').submit(function(event) {
        event.preventDefault();

        var formData = new FormData(this);
        formData.append('event', 'player');

        // Add clubId and sportsId to FormData
        formData.append('clubId', $('#clubName').val());
        formData.append('sportsId', $('#sportsName').val());

        $.ajax({
            url: 'servlet',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(response) {
                if (response.trim() === "done") {
                    alert('Added successfully');
                } else {
                    alert('Failed to add player or photo');
                }
            },
            error: function() {
                alert('Error in form submission.');
            }
        });
    });
});
