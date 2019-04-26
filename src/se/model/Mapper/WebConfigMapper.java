/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/26
 */

package se.model.Mapper;

import se.model.WebsiteConfig;

public interface WebConfigMapper {
    WebsiteConfig QueryConfig();

    int UpdateConfig(WebsiteConfig data);
}
