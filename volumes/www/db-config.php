<?php
  // Current Container Name : crenginxphp
  $dbData = [];
  // add mySQL data
  // add postgres data
    $port = '5432';
    $host = '172.22.0.3';
    //$dbName = 'tambora';  //todo: handle multiple comma seperated
      $dbName = 'tambora';
      $user = 'testuser';
      $password = 'secret';
      $dbData['pgsql:'.$dbName] = 
       ['type' => 'pgsql', 'host' => $host, 'port' => $port, 
        'dbname' => $dbName, 'user' => $user, 'password' => $password, 
         'pdo' => "pgsql:host=$host;port=$port;dbname=$dbName;user=$user;password=$password"];
  // add sqlite data
    $dbName = 'light';
    $host = '';
    $fileName='/cre/www/sqlite/'.$dbName.'.db'; 
    $dbData['sqlite:'.$dbName] = 
     ['type' => 'sqlite', 'host' => $host, 'port' => null, 
      'dbname' => $dbName, 'user' => $dbName, 'password' => null, 
       'pdo' => "sqlite:$fileName"];
  function inqDbConnections()
  {
    global $dbData; 
    return $dbData; 
  }
?>
