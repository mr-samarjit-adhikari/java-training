<?php
echo "Hello world PHP!\n";
$callback = function()
{
	echo "Anonymous function demo\n";
};
echo $callback();
?>
