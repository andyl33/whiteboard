jQuery(document).ready(function($){
    initBoard();
});

function initBoard() {

    // Add sortable behaviour to each column (includes drag & drop between columns via the connectWith attribute)
    $(".column_tickets").sortable({
        connectWith: '.column_tickets',
        placeholder: 'ticket_placeholder',
        forcePlaceholderSize: true,
        items: 'li:not(.column_header)',
        receive: function(event, ui) {
            $.post('/whiteboard/whiteboard/update', { newColumn: $(this).attr("id") },
                function(data) {
                    alert(data);
                }
            );
        }
    });

    $('.board_ticket .info').hide();

    $('.board_ticket .details').click(function(e) {
        e.preventDefault();
        $(this).text($(this).text() == 'More' ? 'Less' : 'More');
        $(this).parents('.board_ticket').find('.info').slideToggle(200);
    });

    $('.expand').click(function() {
        if ($(this).text() == '+') {
            $(this).parents('.column_tickets').find('.info').slideDown(200);
            $(this).text('-');
            $(this).parents('.column_tickets').find('.details').text('Less');

        } else {
            $(this).parents('.column_tickets').find('.info').slideUp(200);
            $(this).text('+');
            $(this).parents('.column_tickets').find('.details').text('More');
        }

        return false;
    });

    $('.board_ticket h3 a').click(function(e) {
        e.preventDefault();
        alert('view ticket');
    });
}
