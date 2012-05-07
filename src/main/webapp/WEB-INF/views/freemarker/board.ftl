<#import "/spring.ftl" as spring/>
<div id="board">
<#list columns?keys as key>
    <ul id="${key}" class="column_tickets">
        <li class="column_header">${key} <a href="#" class="expand" title="Expand all">+</a></li>
        <#list columns[key].tickets as ticket>
            <li id="board_ticket_${ticket.id?string("0")}" class="board_ticket ${ticket.priority}">
                <h3><a href="#">#${ticket.id?string("0")}: ${ticket.summary}</a></h3>
                <div>
                    <div class="info">
                        <div class="ticket_number" style="display:none">${ticket.id?string("0")}</div>
                        <div class="ticket_milestone" style="display:none">${ticket.milestone?string("0")}</div>
                        <#if gravatarEnabled == true>
                            <img src="http://www.gravatar.com/avatar/?s=50" alt="" />
                        </#if>
                        <dl>
                            <dt>Status</dt>
                            <dd class="ticket_status">${ticket.status}</dd>
                            <dt>Type</dt>
                            <dd class="ticket_type" >${ticket.type}</dd>
                            <dt>Priority</dt>
                            <dd class="ticket_priority" >${ticket.priority}</dd>
                            <dt>Owner</dt>
                            <dd class="ticket_owner">${ticket.owner}</dd>
                        </dl>
                    </div>
                    <a href="#" class="details">More</a>
                </div>
            </li>
        </#list>
    </ul>
</#list>
</div>