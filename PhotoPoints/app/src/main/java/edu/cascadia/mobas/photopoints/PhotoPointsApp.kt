class PhotoPointsApp:Application {
   companion object {
      // device unique prefix for key preverences
      private const val KEY_PREFERENCES="PhotoPointsNCF"

      // App is a singleton of and Application, its instance stored in instance
      private lateinit var instance:App

      // sharedPreference stores the most primitive credentials needed to begin function
      private val sharedPreference by lazy { (KEY_PREFERENCES, Context.MODE_PRIVATE) }

      // this initializes the repository
      private val repository: PhotoPointsRepository by lazy { PhotoPointsRepositoryImpl(sharedPreferences) } 
   }

   override fun (onCreate) {
      super.onCreate()
      instance = this
   }
