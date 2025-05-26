<#import "/spring.ftl" as spring/>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
    <title>WinkBall - Whiteboard</title>

    <link rel="stylesheet" type="text/css" href="/css/normalise.css"/>
    <link rel="stylesheet/less" type="text/css" href="/css/whiteboard.less"/>
    
    <script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.8.17.min.js"></script>
    
    <script type="text/javascript" src="/js/less-1.2.1.min.js"></script>
    
    <script type="text/javascript" src="/js/whiteboard.js"></script>
</head>
<body>
	<form id="container" method="post" action="/whiteboard">
		<header>
			<h1>Workflow for Milestone:</h1>
            <select name="milestone" id="select-milestone">
                <#list milestones?keys as value>
                <option value="${value}">${milestones[value]}</option>
                </#list>
            </select>
            <script type="text/javascript">
                $('#select-milestone').change(function(event) {
                    $.post('/whiteboard', { selectedMilestone: $('#select-milestone').val() },
                            function(data) {
                                $('#board-container').html(data);
                                initBoard();
                            }
                    );
                });
            </script>
	        <input type="submit" id="board-submit" class="btn" name="board_submit" value="Save Changes" />
		</header>
	    <div id="board-container">
	        <!-- Content added here -->
	        <input type="hidden" id="board_changes" name="board_changes" value="" />
	    </div>
	</form>
</body>
</html>