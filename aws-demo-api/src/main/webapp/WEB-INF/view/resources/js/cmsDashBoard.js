 
  var cmsModule = angular.module('myCms', ['ngTouch', 'ui.grid']);
		  
		  cmsModule.controller('CMSController', function($scope,$http) {
			  
			  
			  $http.get("ec2/dropdowndata").success( function(response) {
				  
				  
			      $scope.regions = response.awsRegion; 
			      $scope.teams = response.dropDowndata; 
			     
			      
			   });
	
			 
			 $scope.columnDefs = [
					{ name:'instanceId', displayName:'Instance ID', width:250,
						cellTemplate:'anchorTemplate.html' },					
					{ name:'privateIP',displayName:'Private IP', width:150, pinnedLeft:true },
					{ name:'state', width:150 },
					{ name:'tags[0].value', displayName:'partner', width:150 },
					{ name:'tags[1].value', displayName:'Team', width:150 },
					{ name:'tags[2].value', displayName:'Environment', width:150 },
				];	
			
				 
			 $scope.commandWindowPopup=function(id,$scope){
					//alert('hi');
					document.getElementById('light_'+id).style.display='block';
					//document.getElementById('fade').style.display='block';
					
				};
			$scope.closeWindowPopup=function(id,$scope){
				//alert('hi');
				document.getElementById('light_'+id).style.display='none';
				//document.getElementById('fade').style.display='block';
				
			};
			
			$scope.runCommand=function(id,$scope){
				
				var cmdCommand=document.getElementById('command_box_'+id).value;
				var jsonObject={"linuxCommand": cmdCommand};
			
				var valueStringify=JSON.stringify(jsonObject);
				
				
				
	    
			
				  $http.post("ec2/sshConnect?id="+id+"&command="+valueStringify).success( function(response) {
					  
					  document.getElementById('command_out_'+id).value= response.data;
				      
				   });
				
			};
			
			$scope.teamFilter = function() {
				
				var team =document.getElementById("team").value;
				var partner=document.getElementById("partner").value;
				var env=document.getElementById("env").value;
				//if(team !=null &partner!=null && env!=null){
					
				if(angular.isDefined(team) && angular.isDefined(partner) && angular.isDefined(env)){
				 $http.get("ec2/ec2instancedetails/"+team+"/"+partner+"/"+env).success( function(response) {
				      $scope.gridOptions = response;
				   });
				}else{
					 $scope.gridOptions=null;
				}
				
				
			};
			$scope.partnerFilter = function() {
				//alert( document.getElementById("partner").value);
				
				var team =document.getElementById("team").value;
				var partner=document.getElementById("partner").value;
				var env=document.getElementById("env").value;
				
				if(angular.isDefined(team) && angular.isDefined(partner) && angular.isDefined(env)){
				 $http.get("ec2/ec2instancedetails/"+team+"/"+partner+"/"+env).success( function(response) {
				      $scope.gridOptions = response;
				   });
				}else{
					 $scope.gridOptions=null;
				}
				
			};
			$scope.envFilter = function() {
				//alert( document.getElementById("env").value);
				
				var team =document.getElementById("team").value;
				var partner=document.getElementById("partner").value;
				var env=document.getElementById("env").value;
				if(angular.isDefined(team) && angular.isDefined(partner) && angular.isDefined(env)){
				 $http.get("ec2/ec2instancedetails/"+team+"/"+partner+"/"+env).success( function(response) {
				      $scope.gridOptions = response;
				   });
				}else{
					 $scope.gridOptions=null;
				}
				
			};
			
			$scope.isValid = function(value) {
			    return !value
			}
});
		