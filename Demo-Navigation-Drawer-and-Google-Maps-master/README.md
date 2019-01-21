#  Android demo Navigation Drawer y Google Maps  

Navigation Drawer  y  Google Maps Demo


## Splash activity
Creación pantalla de bienvenida

```java
package menurestaurante.proyecto.mauriciogarcia.ejemplonaviationdrawer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}

```
#
![1](https://cloud.githubusercontent.com/assets/25255847/26516514/85c20c82-4244-11e7-9e74-844c8d9e8483.png)


# Navigation drawer

![2](https://cloud.githubusercontent.com/assets/25255847/26516516/885a8122-4244-11e7-9392-7dce2cacc5a5.png)
#
Para acceder desde el  naviagation drawer al mapa de ubicación
```java

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Accion inicio
        } else if (id == R.id.nav_categorias) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_contactame) {
                ContactUsFragment contactUsFragment = new ContactUsFragment();
                FragmentManager   manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
```
# Crear un mapa personalizado
Antes de empezar a utilizar el  API en nuestras aplicaciones será necesario realizar algunos preparativos, y es que para hacer uso de los servicios de Google Maps es necesario que previamente generemos una Clave de API (o API key) asociada a nuestra aplicación. Éste es un proceso sencillo y se realiza accediendo a la  Consola de Desarrolladores https://console.developers.google.com/  de Google..

En el segundo paso tendremos que poner un nombre descriptivo a la clave de API que se va a generar, no es demasiado relevante, por lo que podemos dejar el que nos proponen por defecto. También en este paso se nos da la posibilidad de poder restringir el uso de este proyecto a determinadas aplicaciones concretas. Como es una práctica baste recomendable vamos a ver cómo hacerlo. Pulsaremos sobre el botón “+ Añadir nombre de paquete y huella digital” y veremos que se solicitan dos datos:

- Nombre de paquete 
- Huella digital de certificado SHA-1
#
La obtenemos con el siguiente comando c:\>"C:\Program Files\Java\jdk1.8.0_91\bin\keytool" -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android. 
#
Configuramos en  build.gradle  
```java
compile 'com.google.android.gms:play-services-maps:10.2.6'
```
#
  Incluimos la clave de API en el archivo AndroidManifest.xm
```xml
<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/google_maps_key" />
        <activity
            android:name=".MapsActivity"
            android:label="@string/title_activity_maps" />

```
#
Añadimos Google Maps dentro de una clase Fragment
```java

public class ContactUsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View v= inflater.inflate(R.layout.fragment_contact_us,container, false);

        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        LatLng  pp= new LatLng(13.8147954,-88.8636758);
        map.addMarker(new MarkerOptions().position(pp).title("Restaurante Ilobasco"));

        float zoomlevel = 16;

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp,zoomlevel));
    }
}

```
![3](https://cloud.githubusercontent.com/assets/25255847/26516515/882bc6de-4244-11e7-8036-a332f59c424c.png)
