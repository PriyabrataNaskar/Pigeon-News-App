# Pigeon-News-App
Pigeon - Multi-Module News App, built with MVI, Clean Architecture, Kotlin❣️

## Features

- Skim through Top Headlines in a RecyclerView
- Open Each News and Share that news with your friend
- See a nice animated dragon who is showing you the error

### Design
#### Home Screen
<img src="https://i.ibb.co/kgV0bjH/Home-Page-Horizontal.jpg" width="256" alt= "Home Screen"/>

#### News Details 
<img src="https://i.ibb.co/12J49mM/News-Detail-Page-Horizontal.jpg" width="256" alt= "News Details Screen"/>

[Figma Prototype](https://www.figma.com/proto/0hqmot7drKti1znfOZV4Q1/News-App?node-id=6%3A81&scaling=scale-down&page-id=0%3A1&starting-point-node-id=6%3A81)

### Module-Diagram
<img src="https://i.ibb.co/RpL2YV0/Multi-module.jpg" width="100%" alt= "Multi-Module"/>

### Architecture-Diagram: MVI
<img src="https://i.ibb.co/n7R1Xm8/mvi.jpg" width="100%" alt= "MVI"/>

### API

All the news are fetched from the news api. If you're cloning this project in your own machine don't forget to modify the ```API KEY```. You will get a new API Key on the [NEWS API](https://newsapi.org) website

### Sample JSON response
```
{
  "status": "ok",
    "sources": [
                  {
                    "id": "australian-financial-review",
                    "name": "Australian Financial Review",
                    "description": "The Australian Financial Review reports the latest news from business, finance, investment and politics, updated in real time. It has a reputation for independent, award-winning journalism and is essential reading for the business and investor community.",
                     "url": "http://www.afr.com",
                     "category": "business",
                     "language": "en",
                     "country": "au"
                    },
                    {
                      "id": "bloomberg",
                      "name": "Bloomberg",
                      "description": "Bloomberg delivers business and markets news, data, analysis, and video to the world, featuring stories from Businessweek and Bloomberg News.",
                      "url": "http://www.bloomberg.com",
                      "category": "business",
                      "language": "en",
                      "country": "us"
                      },
                      {
                      "id": "business-insider",
                      "name": "Business Insider",
                      "description": "Business Insider is a fast-growing business site with deep financial, media, tech, and other industry verticals. Launched in 2007, the site is now the largest business news site on the web.",
                      "url": "http://www.businessinsider.com",
                      "category": "business",
                      "language": "en",
                      "country": "us"
                      }
                ]
}

```

### Libraries & Tools

- MVI with Clean Architecture: Simplify state management + Better Re-usability
- Hilt: Dependency Injection Framework on Android on top of Dagger
- Navigation Components + Safe Args: Simplified navigation between fragments with the help of generated code. Safe-Args gradle plugin simplifies the process of sending data between fragments with null check support.
- Kotlin❣️: There's no second thought of other languages
- Build Src + Kotlin DSL: Easy to manage dependency in Multi-Module
- Single Activity Architecture: Using only one activity to host all the fragments. This simplifies using nav-graph.
- [Glide](https://github.com/bumptech/glide): Easy to load images over the internet
- [Shimmer](https://github.com/facebook/shimmer-android): Library by Facebook for loading effect. Help to improve user experience.
- [Retrofit](https://square.github.io/retrofit/): Simplify Network Calls on background thread
- Lottie: Play animation on the error screen or when the Internet is unavailable
- [GSON Library](https://github.com/google/gson): Converting JSON to POJO/Kotlin Data Class & vice-versa

## Resources

- [Network Module with Retrfoit](https://proandroiddev.com/modeling-retrofit-responses-with-sealed-classes-and-coroutines-9d6302077dfe)

## Getting Started

- To get started with this project, you'll need to clone the repository and open it in Android Studio.
- Make sure you're using updated Android Studio to support Android Gradle Plugin 8 and above.
- Create a file on root directory: ```credentials.properties``` and add ```NEWS_API_KEY= YOUR_API_KEY```
- Sync & Build Project
- You should be able to build the APK on your local machine

## Usage

This project can be used as a template for other developers who want to learn Multi-Module with MVI & Clean Architecture.

## Contributing

If you'd like to contribute to this project, please feel free to submit a pull request. We welcome contributions from all developers who are interested in learning more about Multi-module with Clean Architecture.

### Find this project useful? ❤️

- Support it by clicking the ⭐ button on the upper right of this page. ✌️

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
