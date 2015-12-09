class Project_operation(Model):
	class Meta:
		db_table = 'project_operation'
		database = db
	operationId = PrimaryKeyField()
	projectId = IntegerField()
	userId = IntegerField()
	fromStatus = IntegerField()
	toStatus = IntegerField()
	type = CharField()
	data = CharField()
	creationTime = CharField()
def addProject_operation(projectId,userId,fromStatus,toStatus,type,data,creationTime):
	print '--addProject_operation--start--'
	create_time = (int)(time.time())
	Project_operation = Project_operation(projectId=projectId,userId=userId,fromStatus=fromStatus,toStatus=toStatus,type=type,data=data,)
	Project_operation.save()
	print '--addProject_operation--end--'
def findProject_operation(operationId):
	result= object
	try:
		ul = Project_operation.select().where(Project_operation.operationId == operationId).get()
		result=ul
	except:
			result=None
	return result
def updateProject_operation(operationId,key,value):
	result= object
	print '---update start---'
	try:
		ul = Project_operation.select().where(Project_operation.operationId == operationId).get()
		if ul:
			#print 'find success'
			if key=='projectId':
				ul.projectId = value
			elif key=='userId':
				ul.userId = value
			elif key=='fromStatus':
				ul.fromStatus = value
			elif key=='toStatus':
				ul.toStatus = value
			elif key=='type':
				ul.type = value
			elif key=='data':
				ul.data = value
			elif key=='creationTime':
				ul.creationTime = value
		else:
			raise 'unkown field:'+key
		ul.save()
	except:
		result=None
	print '---update Project_operationstart---'
def findAllProject_operation():
	#     print '---find all start---'
	result= object
	try:
		ulist = Project_operation.select()
		result=ulist
	except:
		result=None
	#     print '---find all end---'
	return result
def print_Project_operation(less):
	print '---print Less start---'
	print 'projectId:'+str(Project_operation.projectId)+'userId:'+str(Project_operation.userId)+'fromStatus:'+str(Project_operation.fromStatus)+'toStatus:'+str(Project_operation.toStatus)+'type:'+Project_operation.type+'data:'+Project_operation.data+'creationTime:'+Project_operation.creationTime+''
	print '---print Project_operation start---'
