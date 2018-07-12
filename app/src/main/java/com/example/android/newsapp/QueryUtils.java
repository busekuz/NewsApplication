package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public final class QueryUtils {

    //private static final String SAMPLE_JSON_RESPONSE =" {\"response\":{\"status\":\"ok\",\"userTier\":\"developer\""
    //       + ",\"total\":131985,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":13199,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"commentisfree/2018/may/27/world-distraction-demands-new-focus\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-05-27T08:00:20Z\",\"webTitle\":\"Technology is driving us to distraction | James Williams\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/may/27/world-distraction-demands-new-focus\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/may/27/world-distraction-demands-new-focus\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"world/shortcuts/2018/apr/18/religion-technology-apps-muslims-halal-cashless-church-donation\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-04-18T16:14:47Z\",\"webTitle\":\"App faith: how religions are embracing technology\",\"webUrl\":\"https://www.theguardian.com/world/shortcuts/2018/apr/18/religion-technology-apps-muslims-halal-cashless-church-donation\",\"apiUrl\":\"https://content.guardianapis.com/world/shortcuts/2018/apr/18/religion-technology-apps-muslims-halal-cashless-church-donation\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"lifeandstyle/2018/jul/08/trek-powerfly-a-raft-of-next-generation-technology-ebike-review\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-07-08T05:00:18Z\",\"webTitle\":\"Trek Powerfly: ‘A raft of next-generation technology’\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/jul/08/trek-powerfly-a-raft-of-next-generation-technology-ebike-review\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/jul/08/trek-powerfly-a-raft-of-next-generation-technology-ebike-review\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"social-care-network/2018/jun/25/xbox-technology-older-people-hospital-admissions\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2018-06-25T09:25:02Z\",\"webTitle\":\"How Xbox technology could keep older people safe at home\",\"webUrl\":\"https://www.theguardian.com/social-care-network/2018/jun/25/xbox-technology-older-people-hospital-admissions\",\"apiUrl\":\"https://content.guardianapis.com/social-care-network/2018/jun/25/xbox-technology-older-people-hospital-admissions\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"football/2018/jun/26/world-cup-fiver-var-portugal-iran-spain-morocco-what-a-business\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-06-26T12:07:22Z\",\"webTitle\":\"The biggest problem with technology is humans | World Cup Fiver\",\"webUrl\":\"https://www.theguardian.com/football/2018/jun/26/world-cup-fiver-var-portugal-iran-spain-morocco-what-a-business\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/jun/26/world-cup-fiver-var-portugal-iran-spain-morocco-what-a-business\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"books/2018/jun/15/rise-of-the-machines-has-technology-evolved-beyond-our-control-\",\"type\":\"article\",\"sectionId\":\"books\",\"sectionName\":\"Books\",\"webPublicationDate\":\"2018-06-15T11:00:15Z\",\"webTitle\":\"Rise of the machines: has technology evolved beyond our control?\",\"webUrl\":\"https://www.theguardian.com/books/2018/jun/15/rise-of-the-machines-has-technology-evolved-beyond-our-control-\",\"apiUrl\":\"https://content.guardianapis.com/books/2018/jun/15/rise-of-the-machines-has-technology-evolved-beyond-our-control-\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"technology/2018/jun/11/uber-drunk-technology-new-ai-feature-patent\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2018-06-11T16:27:24Z\",\"webTitle\":\"Uber developing technology that would tell if you're drunk\",\"webUrl\":\"https://www.theguardian.com/technology/2018/jun/11/uber-drunk-technology-new-ai-feature-patent\",\"apiUrl\":\"https://content.guardianapis.com/technology/2018/jun/11/uber-drunk-technology-new-ai-feature-patent\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2018/may/31/venice-architecture-biennale-canada-indigenous-exhibit\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-05-31T11:37:24Z\",\"webTitle\":\"Canada's indigenous architecture Biennale exhibit weaves nature, culture and technology\",\"webUrl\":\"https://www.theguardian.com/world/2018/may/31/venice-architecture-biennale-canada-indigenous-exhibit\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/may/31/venice-architecture-biennale-canada-indigenous-exhibit\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"technology/2018/feb/27/apple-launching-technology-enabled-healthcare-service\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2018-02-27T10:55:54Z\",\"webTitle\":\"Apple to launch 'technology enabled' healthcare service\",\"webUrl\":\"https://www.theguardian.com/technology/2018/feb/27/apple-launching-technology-enabled-healthcare-service\",\"apiUrl\":\"https://content.guardianapis.com/technology/2018/feb/27/apple-launching-technology-enabled-healthcare-service\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"environment/2018/may/10/new-technology-slash-aluminium-production-carbon-emissions\",\"type\":\"article\",\"sectionId\":\"environment\",\"sectionName\":\"Environment\",\"webPublicationDate\":\"2018-05-10T18:30:19Z\",\"webTitle\":\"New technology could slash carbon emissions from aluminium production\",\"webUrl\":\"https://www.theguardian.com/environment/2018/may/10/new-technology-slash-aluminium-production-carbon-emissions\",\"apiUrl\":\"https://content.guardianapis.com/environment/2018/may/10/new-technology-slash-aluminium-production-carbon-emissions\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    public QueryUtils(){

    }

    public static ArrayList<New> extractJson(){
        CustomUrl myurl = new CustomUrl("https://content.guardianapis.com/search?q=%27technology%27&show-fields=all&api-key=01216ad2-f602-42c3-a90b-0a1f5e980937");
        String SAMPLE_JSON_RESPONSE = null;
        try {
            SAMPLE_JSON_RESPONSE = myurl.getHTML();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<New> news = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray resultArray = response.getJSONArray("results");

            int i;
            for(i = 0 ; i < resultArray.length() ; i++){

                JSONObject latestNew = resultArray.getJSONObject(i);
                JSONObject fields = latestNew.getJSONObject("fields");
                String body = fields.getString("body");

                String titleNew = latestNew.getString("webTitle");
                String titleSec = latestNew.getString("sectionName");
                String url = latestNew.getString("webUrl");


                New techNew = new New(titleNew,titleSec, url,body);

                news.add(techNew);
                Log.v("QueryUtils","Title -- " + titleNew + "Section -- " + titleSec + "length:  " + resultArray.length());

            }

        }catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return news;
    }
}
