package lilypuree.decorative_winter;

import lilypuree.decorative_winter.core.setup.BlockActivateEventHandler;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class Callbacks {
    public static void initCallbacks() {
        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
            BlockActivateEventHandler eventHandler = new BlockActivateEventHandler(world, hitResult.getBlockPos(), player, hand, player.getItemInHand(hand));
            return eventHandler.getResult();
        }));
    }
}
