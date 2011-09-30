jQuery(document).ready(function($){

    // Add sortable behaviour to each column (includes drag & drop between columns via the connectWith attribute)
    $(".column_tickets").sortable({
        connectWith: '.column_tickets',
        placeholder: 'ticket_placeholder',
        forcePlaceholderSize: true
    });

});