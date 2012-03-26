<#import "/spring.ftl" as spring/>
<div id="board">
    <ul id="todo" class="column_tickets">
        <li class="column_header">To do <a href="#" class="expand" title="Expand all">+</a></li>
    <#list todo as ticket>
        <li id="board_ticket_{$ticket.id}" class="board_ticket ${ticket.priority}">
            <h3><a href="#">#${ticket.id}: ${ticket.summary}</a></h3>
            <div>
                <div class="info">
                    <#if gravatarEnabled == true>
                        <img src="http://www.gravatar.com/avatar/?s=50" alt="" />
                    </#if>
                    <dl>
                        <dt>Status</dt>
                        <dd>${ticket.status}</dd>
                        <dt>Type</dt>
                        <dd>${ticket.type}</dd>
                        <dt>Priority</dt>
                        <dd>${ticket.priority}</dd>
                        <dt>Owner</dt>
                        <dd>${ticket.owner}</dd>
                    </dl>
                </div>
                <a href="#" class="details">More</a>
            </div>
        </li>
    </#list>
    </ul>
    <ul id="inprogress" class="column_tickets">
        <li class="column_header">In progress <a href="#" class="expand" title="Expand all">+</a></li>
    <#list inprogress as ticket>
        <li id="board_ticket_${ticket.id}" class="board_ticket ${ticket.priority}">
            <h3><a href="">#${ticket.id}: ${ticket.summary}</a></h3>
            <div>
                <div class="info">
                    <#if gravatarEnabled == true>
                        <img src="http://www.gravatar.com/avatar/?s=50" alt="" />
                    </#if>
                    <dl>
                        <dt>Status</dt>
                        <dd>${ticket.status}</dd>
                        <dt>Type</dt>
                        <dd>${ticket.type}</dd>
                        <dt>Priority</dt>
                        <dd>${ticket.priority}</dd>
                        <dt>Owner</dt>
                        <dd>${ticket.owner}</dd>
                    </dl>
                </div>
                <a href="#" class="details">More</a>
            </div>
        </li>
    </#list>
    </ul>
    <ul id="reviewing" class="column_tickets">
        <li class="column_header">In review <a href="#" class="expand" title="Expand all">+</a></li>
    <#list review as ticket>
        <li id="board_ticket_${ticket.id}" class="board_ticket ${ticket.priority}">
            <h3><a href="">#${ticket.id}: ${ticket.summary}</a></h3>
            <div>
                <div class="info">
                    <#if gravatarEnabled == true>
                        <img src="http://www.gravatar.com/avatar/?s=50" alt="" />
                    </#if>
                    <dl>
                        <dt>Status</dt>
                        <dd>${ticket.status}</dd>
                        <dt>Type</dt>
                        <dd>${ticket.type}</dd>
                        <dt>Priority</dt>
                        <dd>${ticket.priority}</dd>
                        <dt>Owner</dt>
                        <dd>${ticket.owner}</dd>
                    </dl>
                </div>
                <a href="#" class="details">More</a>
            </div>
        </li>
    </#list>
    </ul>
    <ul id="testing" class="column_tickets">
        <li class="column_header">Testing <a href="#" class="expand" title="Expand all">+</a></li>
    <#list testing as ticket>
        <li id="board_ticket_${ticket.id}" class="board_ticket ${ticket.priority}">
            <h3><a href="">#${ticket.id}: ${ticket.summary}</a></h3>
            <div>
                <div class="info">
                    <#if gravatarEnabled == true>
                        <img src="http://www.gravatar.com/avatar/?s=50" alt="" />
                    </#if>
                    <dl>
                        <dt>Status</dt>
                        <dd>${ticket.status}</dd>
                        <dt>Type</dt>
                        <dd>${ticket.type}</dd>
                        <dt>Priority</dt>
                        <dd>${ticket.priority}</dd>
                        <dt>Owner</dt>
                        <dd>${ticket.owner}</dd>
                    </dl>
                </div>
                <a href="#" class="details">More</a>
            </div>
        </li>
    </#list>
    </ul>
    <ul id="done" class="column_tickets">
        <li class="column_header">Done <a href="#" class="expand" title="Expand all">+</a></li>
    <#list done as ticket>
        <li id="board_ticket_${ticket.id}" class="board_ticket ${ticket.priority}">
            <h3><a href="">#${ticket.id}: ${ticket.summary}</a></h3>
            <div>
                <div class="info">
                    <#if gravatarEnabled == true>
                        <img src="http://www.gravatar.com/avatar/?s=50" alt="" />
                    </#if>
                    <dl>
                        <dt>Status</dt>
                        <dd>${ticket.status}</dd>
                        <dt>Type</dt>
                        <dd>${ticket.type}</dd>
                        <dt>Priority</dt>
                        <dd>${ticket.priority}</dd>
                        <dt>Owner</dt>
                        <dd>${ticket.owner}</dd>
                    </dl>
                </div>
                <a href="#" class="details">More</a>
            </div>
        </li>
    </#list>
    </ul>
</div>