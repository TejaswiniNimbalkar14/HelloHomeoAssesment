# HelloHomeoAssesment


Required dependencies:

1. Retrofit - To fetch data from API
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
2. Retrofit gson convertor - to covert gson file
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
3. Recycler View - to show data in recycler view
    implementation "androidx.recyclerview:recyclerview:1.1.0"
4. Card View - to show one meber data on Card
    implementation "androidx.cardview:cardview:1.0.0"
5. Glide - to load image into image view
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
6. Room Persistence library
    implementation "androidx.room:room-runtime:2.3.0"
    annotationProcessor "androidx.room:room-compiler:2.3.0"
    
    
Execution Flow:

1. When app run for the first time, if ineternet is connected, data from API will be shown otherwise from room database which will be null for the first time.
2. Then data fetched will be added to room database
3. After that, every time when activity will run data either from internet or room db will be fetched.
4. Refresh button click will result in the same as above(3.).
5. Delete button click will delete all the data from room database.
