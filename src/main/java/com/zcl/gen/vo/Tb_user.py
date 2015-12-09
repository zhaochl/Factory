class Less3(Model):
	class Meta:
		db_table = 'search_less3_exception_log'
		database = db
	id = PrimaryKeyField()
	sex = IntegerField()
	gid = IntegerField()
