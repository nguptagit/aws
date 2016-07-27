 
  var cmsModule = angular.module('myCms', ['ngTouch', 'ui.grid', 'ui.grid.autoResize']);
		  
		  cmsModule.controller('CMSController', function($scope) {
	
		  $scope.teams = {
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
                };
			 
			 $scope.columnDefs = [
					{ name:'instanceId', displayName:'Instance ID', width:100 },
					{ name:'privateIP',displayName:'Private IP', width:100, pinnedLeft:true },
					{ name:'state', width:150 },
					{ name:'tags[0].Team', displayName:'Team', width:150 },
					{ name:'tags[1].Partner', displayName:'partner', width:150 },
					{ name:'tags[2].env', displayName:'Environment', width:150 },
				];	
			
				 
			$scope.teamFilter = function() {
				alert( document.getElementById("team").value);
				
				$scope.gridOptions = [ {
					"instanceId": "123",
					"privateIP": "wwe",
					"state":"Active",
					"tags":[{"Team":"cms"},{"Partner":"HBO"},{"env":"dev"}]
				},
				{
					"instanceId": "345",
					"privateIP": "hbohome",
					"state": "Active",
					"tags":[{"Team":"cms"},{"Partner":"GoPro"},{"env":"dev"}]
				},
				{
					"instanceId": "789",
					"privateIP": "godaday",
					"state":"Active",
					"tags":[{"Team":"cms"},{"Partner":"Arena"},{"env":"dev"}]
				},
				{
					"instanceId": "345",
					"privateIP": "hbohom11e",
					"state":"Active",
					"tags":[{"Team":"cms"},{"Partner":"wwe"},{"env":"dev"}]
				}
				];	
				
			};
			
			$scope.partnerFilter = function() {
				alert( document.getElementById("partner").value);
				
			};
			$scope.envFilter = function() {
				alert( document.getElementById("env").value);
				
			};
			
			
			
			
			
		
			});
		