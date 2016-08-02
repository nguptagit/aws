 
  var cmsModule = angular.module('myCms', ['ngTouch', 'ui.grid']);
		  
		  cmsModule.controller('CMSController', function($scope,$http) {
			  
			  
			  $http.get("ec2/dropdowndata").success( function(response) {
				  
				  
			      $scope.regions = response.awsRegion; 
			      $scope.teams = response.dropDowndata; 
			     
			      
			   });
	
		 /* $scope.regions = {
                    'Asia Pacific':{
							'CMS': {
								'GoPro': ['Dev', 'QA', 'PRD'],
								'WWE': ['Dev', 'QA', 'PRD'],  
								},
								'CMSBUS': {
								  'ARINA': ['Dev', 'QA', 'PRD'],
								  'HBO': ['Dev', 'QA', 'PRD']
								},
								'CMSUI': {
									'Times': ['Dev', 'QA', 'PRD'],
									'TvToday': ['Dev', 'QA', 'PRD']
								}
						},
					'EU':{
						'CMS': {
								'GoPro': ['Dev', 'QA', 'PRD'],
								'WWE': ['Dev', 'QA', 'PRD'],  
								}
						}
                };*/
			 
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
				alert('cming soon');
				
				//document.getElementById('fade').style.display='block';
				
			};
			
			$scope.teamFilter = function() {
				//alert( document.getElementById("region").value);
				
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
		